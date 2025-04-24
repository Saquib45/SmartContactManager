package com.scm.controllers;

// import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.config.OAuthAuthenticationSuccessHandler;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.helper.message;
import com.scm.helper.messageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    

    private Logger logger=LoggerFactory.getLogger(ContactController.class);

    private final OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler;
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    ContactController(OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler) {
        this.OAuthAuthenticationSuccessHandler = OAuthAuthenticationSuccessHandler;
    }
    //add contact page hadler
    @RequestMapping("/add")
    public String addContactView(Model model){ 
        ContactForm contactForm = new ContactForm();
        contactForm.setName("sakib");
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result,Authentication authentication,HttpSession session) {
        //process the form data and save the contact
        // add validation logic here

        if(result.hasErrors()){
            System.out.println(result);
            session.setAttribute("message", message.builder()
            .content("Please correct the errors")
            .type(messageType.red)
            .build());
            return "user/add_contact";
        }


        //I have to done this part


        String username=Helper.getEmailOfLoggedInUser(authentication);
        //form----->contact
        User user=userService.getUserByEmail(username);

        //process the contact picture
        String fileName=UUID.randomUUID().toString();
        //image processing
       String fileURL=imageService.uploadImage(contactForm.getContactImage(),fileName);


        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setUser(user);
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);
        contactService.save(contact);

        //set the contact picture url

        session.setAttribute("message", message.builder()
        .content("your contact is saved")
        .type(messageType.green)
        .build());
        //set the message to be displayed
        return "redirect:/user/contacts/add";
    }

    //view contacts
    @RequestMapping
    public String viewContacts(
        @RequestParam(value = "page",defaultValue = "0") int page,
        @RequestParam(value = "size",defaultValue = AppConstants.PAGE_SIZE+"") int size,   
        @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
        @RequestParam(value = "direction",defaultValue ="asc") String direction,Authentication authentication,Model model){
        //load all the user contacts
        String username=Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);
        Page<Contact> pageContact=contactService.getByUser(user,page,size,sortBy,direction);
        model.addAttribute("pageContact",pageContact);
        model.addAttribute("pageSize",AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm",new ContactSearchForm());

        return "user/contacts";
    }

    //search contacts
    @RequestMapping("/search")
    public String searchHandler(
        @ModelAttribute ContactSearchForm contactSearchForm,
        @RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+"") int size,
        @RequestParam(value = "page",defaultValue = "0") int page,
        @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
        @RequestParam(value = "direction",defaultValue = "asc") String direction,
        Model model, Authentication authentication
        ){
           
            logger.info("field {} keyword {}",contactSearchForm.getField(),contactSearchForm.getValue());
           var user=userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
           Page<Contact> pageContact=null;

           if(contactSearchForm.getField().equalsIgnoreCase("name")){
            pageContact = contactService.searchByName(contactSearchForm.getValue(),size,page,sortBy,direction,user);
           }
           else if(contactSearchForm.getField().equalsIgnoreCase("email")){
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(),size,page,sortBy,direction,user);
           }
           else if(contactSearchForm.getField().equalsIgnoreCase("phone")){
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(),size,page,sortBy,direction,user);
           }

           else{
            logger.error("invalid search field: {}",contactSearchForm.getField());
           }

           logger.info("pageContact {}",pageContact);

           model.addAttribute("contactSearchForm", contactSearchForm);
           
           model.addAttribute("pageContact", pageContact);

           model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "user/search";
    }
    
}

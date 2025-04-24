package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
// import com.scm.controllers.ContactController;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    // private final ContactController contactController;
    @Autowired
    private ContactRepo contactRepo;

    // ContactServiceImpl(ContactController contactController) {
    //     this.contactController = contactController;
    // }
    
    @Override
    public Contact save(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact object cannot be null");
        }
        String contactId=UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);    
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();

    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with given id" + id));
    }

    @Override
    public void delete(String id) {
        var contact=contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with given id" + id));
        contactRepo.delete(contact);    
    }

    @Override
    public List<Contact> getByUserId(String userid) {
        return contactRepo.findByUserId(userid);
    }

    @Override
    public Page<Contact> getByUser(User user,int page ,int size , String sortBy, String direction) {
        
        Sort sort=direction.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
       
        var pageable=PageRequest.of(page,size,sort);

        
        return contactRepo.findByUser(user,pageable);
    }



    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order,User user) {
       
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
       var pageable=PageRequest.of(page,size,sort);

        return contactRepo.findByUserAndNameContaining(user,nameKeyword,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User user) {
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable=PageRequest.of(page,size,sort);
 
         return contactRepo.findByUserAndEmailContaining(user,emailKeyword,pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberkeyword, int size, int page, String sortBy, String order,User user) {
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
       var pageable=PageRequest.of(page,size,sort);

        return contactRepo.findByUserAndPhoneNumberContaining(user,phoneNumberkeyword,pageable);
    }

    

}

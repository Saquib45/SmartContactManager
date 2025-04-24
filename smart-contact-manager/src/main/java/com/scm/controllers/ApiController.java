package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.entities.Contact;
import com.scm.services.ContactService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class ApiController {

    //get contact
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{contactid}")
    public Contact getContact(@PathVariable String contactid){
        return contactService.getById(contactid);
    }
}

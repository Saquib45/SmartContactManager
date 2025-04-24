package com.scm.services;

import java.util.List;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);

    //search contact
    Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String order,User user);
    Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String order,User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberkeyword,int size,int page,String sortBy,String order,User user);

    List<Contact> getByUserId(String userid);
    Page<Contact> getByUser(User user,int page,int size,String sortfield,String sortDirectioString);
}

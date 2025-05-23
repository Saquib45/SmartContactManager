package com.scm.services;

public interface EmailService {


    void sendEmail(String to, String subject, String body);

    void sendEmailWithHtml();

    void sendEmailWithAttach(String to, String subject, String body);

}

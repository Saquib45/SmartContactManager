package com.scm.services.impl;

import org.springframework.mail.javamail.JavaMailSender;

import com.scm.services.EmailService;

public class EmailServiceImpl implements EmailService{

    @Override
    public void sendEmail(String to, String subject, String body) {
       private JavaMailSender eMail
    }

    @Override
    public void sendEmailWithHtml() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithHtml'");
    }

    @Override
    public void sendEmailWithAttachment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
    }

}

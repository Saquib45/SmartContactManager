package com.scm.services;

public interface EmailService {

    @com
    void sendEmail(String to, String subject, String body);

    void sendEmailWithHtml();

    void sendEmailWithAttachment();

}

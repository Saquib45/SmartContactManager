package com.scm.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface EmailService {

    @Compo
    void sendEmail(String to, String subject, String body);

    void sendEmailWithHtml();

    void sendEmailWithAttachment();

}

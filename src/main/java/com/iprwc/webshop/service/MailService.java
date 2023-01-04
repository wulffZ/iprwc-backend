package com.iprwc.webshop.service;

import com.iprwc.webshop.EmailDetails;

public interface MailService {
    void sendEmail(EmailDetails details);
}

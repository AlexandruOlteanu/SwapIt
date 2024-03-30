package com.swapit.user.service;

public interface EmailSenderService {
    void sendSimpleEmail(String to, String subject, String body);
}

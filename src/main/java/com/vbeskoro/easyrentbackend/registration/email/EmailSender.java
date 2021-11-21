package com.vbeskoro.easyrentbackend.registration.email;

public interface EmailSender {
    void send(String to, String email);
}

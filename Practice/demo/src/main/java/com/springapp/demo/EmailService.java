package com.springapp.demo;

public class EmailService{
    private final EmailClient emailClient;

    public EmailService(EmailClient emailClient){
        this.emailClient = emailClient;
    }

    public void sendEmail(){
        emailClient.connect();
        System.out.println("Email Sent");
    }
}
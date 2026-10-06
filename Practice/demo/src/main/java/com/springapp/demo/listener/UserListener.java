package com.springapp.demo.listener;

import com.springapp.demo.event.UserCreatedEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class UserListener{

    @EventListener
    public void handleUserCreated(UserCreatedEvent event){
        System.out.println("Sending welcome mail to " + event.getUserName());
    }
}
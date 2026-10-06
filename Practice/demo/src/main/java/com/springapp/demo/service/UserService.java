package com.springapp.demo.service;

import com.springapp.demo.event.UserCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }

    public void createUser(){
        System.out.println("User creation started");

        Long userId = 101L;
        String userName = "John";

        eventPublisher.publishEvent(new UserCreatedEvent(userId, userName));

        System.out.println("User creation ended");
    }
}


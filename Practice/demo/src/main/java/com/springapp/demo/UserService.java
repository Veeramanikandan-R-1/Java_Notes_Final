package com.springapp.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;


@Component
public class UserService {
    public UserService() {
        System.out.println("1. Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. @PostConstruct");
    }

    public void execute() {
        System.out.println("3. Bean is being used");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("4. @PreDestroy");
    }
}

package com.practice.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LoginController {

    @RequestMapping("/hello")
    public String hello() {
        return "Welcome to Spring Boot!";
    }
}
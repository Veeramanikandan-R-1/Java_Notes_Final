package com.practice.ecommerce_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
@RequestMapping ("/api")
public class ProductController {
    
    @GetMapping
    public String greet() {
        System.out.println("Hello, Welcome to the E-commerce API!");
        return "Hello, Welcome to the E-commerce API!";
    }
    
}

package com.practice.ecommerce_server.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.ecommerce_server.model.Product;
import com.practice.ecommerce_server.service.ProductService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController 
@RequestMapping ("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    
    @GetMapping
    public String greet() {
        System.out.println("Hello, Welcome to the E-commerce API!");
        return "Hello, Welcome to the E-commerce API!";
    }
    
    @GetMapping("/products")
    public List<Product> getProducts() {
        System.out.println("Fetching all products...");
        return service.getAllProducts();
    }
}

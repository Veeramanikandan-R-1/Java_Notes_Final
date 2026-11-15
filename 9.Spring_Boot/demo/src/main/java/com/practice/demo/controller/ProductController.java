package com.practice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.model.Product;
import com.practice.demo.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class ProductController {
    
    @Autowired 
    ProductService productService;

    @RequestMapping("/products")    
    public Product[] getProducts() {
        return productService.getProducts();
    }
}

package com.practice.ecommerce_server.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.ecommerce_server.model.Product;
import com.practice.ecommerce_server.service.ProductService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@RestController 
@CrossOrigin(origins = "https://glorious-tribble-q59vg445r5fxp5p-5173.app.github.dev/")
@RequestMapping ("/api")
public class ProductController {
    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }
    
    @GetMapping
    public String greet() {
        System.out.println("Hello, Welcome to the E-commerce API!");
        return "Hello, Welcome to the E-commerce API!";
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        System.out.println("Fetching all products...");
        return new ResponseEntity<>(service.getAllProducts(), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {
        Product product = service.getProduct(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

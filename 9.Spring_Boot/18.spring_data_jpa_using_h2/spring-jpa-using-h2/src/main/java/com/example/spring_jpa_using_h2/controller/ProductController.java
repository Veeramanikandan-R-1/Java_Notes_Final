package com.example.spring_jpa_using_h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_jpa_using_h2.model.Product;
import com.example.spring_jpa_using_h2.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController 
public class ProductController {
    
    @Autowired 
    ProductService productService;

    @GetMapping("/products")    
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/product/{prodId}")
    public Product getProduct(@PathVariable int prodId) {
        return productService.getProduct(prodId);
    }
    
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        //TODO: process POST request
        productService.addProduct(product);
        return "Product added successfully";
    }

    @PutMapping("/product/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "Product updated successfully";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}

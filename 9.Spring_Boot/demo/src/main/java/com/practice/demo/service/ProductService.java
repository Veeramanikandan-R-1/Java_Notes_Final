package com.practice.demo.service;

import org.springframework.stereotype.Service;

import com.practice.demo.model.Product;

@Service 
public class ProductService {
    public Product[] getProducts() {
        Product[] products = new Product[3];
        products[0] = new Product(1, "Product 1", 100);
        products[1] = new Product(2, "Product 2", 200);
        products[2] = new Product(3, "Product 3", 300);
        return products;
    }
}

package com.practice.ecommerce_server.service;
import java.util.List;

import com.practice.ecommerce_server.model.Product;
import com.practice.ecommerce_server.repo.ProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
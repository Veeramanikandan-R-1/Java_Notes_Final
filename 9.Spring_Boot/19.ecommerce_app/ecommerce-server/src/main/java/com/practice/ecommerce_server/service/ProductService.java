package com.practice.ecommerce_server.service;
import java.util.List;

import com.practice.ecommerce_server.model.Product;
import com.practice.ecommerce_server.repo.ProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProduct(int productId) {
        return productRepo.findById(productId).orElse(null);
    }
}
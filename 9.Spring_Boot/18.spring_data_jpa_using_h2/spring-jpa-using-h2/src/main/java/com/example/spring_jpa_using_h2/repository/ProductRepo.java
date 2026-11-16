package com.example.spring_jpa_using_h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_jpa_using_h2.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}

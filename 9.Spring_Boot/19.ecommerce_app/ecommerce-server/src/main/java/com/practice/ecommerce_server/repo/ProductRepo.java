package com.practice.ecommerce_server.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.ecommerce_server.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // Custom query methods can be defined here if needed
}
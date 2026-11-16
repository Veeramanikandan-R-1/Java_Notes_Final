package com.example.spring_jpa_using_h2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_jpa_using_h2.model.Product;
import com.example.spring_jpa_using_h2.repository.ProductRepo;

@Service 
public class ProductService {

    @Autowired 
    ProductRepo productRepo;

    //  private List<Product> products = new ArrayList<>(List.of(
    //     new Product(1, "Product 1", 100),
    //     new Product(2, "Product 2", 200),
    //     new Product(3, "Product 3", 300)
    // ));

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProduct(int prodId) {
        return productRepo.findById(prodId).orElse(null);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public void updateProduct(int id, Product updatedProduct) {
        productRepo.save(updatedProduct);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }
}

package com.practice.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.demo.model.Product;

@Service 
public class ProductService {
     private List<Product> products = new ArrayList<>(List.of(
        new Product(1, "Product 1", 100),
        new Product(2, "Product 2", 200),
        new Product(3, "Product 3", 300)
    ));

    public Product[] getProducts() {
        return products.toArray(new Product[0]);
    }

    public Product getProduct(int prodId) {
        for (Product product : products) {
            if (product.getProdId() == prodId) {
                return product;
            }
        }
        return null; 
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void updateProduct(int id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProdId() == id) {
                products.set(i, updatedProduct);
                return;
            }
        }
    }

    public void deleteProduct(int id) {
        products.removeIf(product -> product.getProdId() == id);
    }
}

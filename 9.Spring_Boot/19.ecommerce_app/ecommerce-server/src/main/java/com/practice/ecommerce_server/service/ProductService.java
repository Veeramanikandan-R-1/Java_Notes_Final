package com.example.demo.service;
import java.io.IOException;
import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        return productRepo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
//        product.setImageData(imageFile.getBytes());
//        product.setImageType(imageFile.getContentType());
//        product.setImageName(imageFile.getOriginalFilename());
        System.out.println("imageFile"+imageFile);
        if (!imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }
        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepo.searchProduct(keyword);
    }
}
package com.example.demo.controller;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
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

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1 = service.updateProduct(id, product, imageFile);
            if(product1 != null){
                return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try{
            Product product1 = service.getProduct(id);
            if(product1 != null){
                service.deleteProduct(id);
                return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

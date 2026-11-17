package com.practice.ecommerce_server.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String brand;
    private String category;
    private Date releaseDate;
    private boolean available;
}


// give query to load he sample data to db

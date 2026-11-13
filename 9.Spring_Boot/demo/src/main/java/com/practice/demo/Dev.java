package com.practice.demo;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Dev{
    private Laptop laptop;
    
    @Autowired
    public void setLaptop(Laptop laptop){
        this.laptop = laptop;
    };

    public void build(){
        laptop.compile();
        System.out.println("building the project");
    };
}
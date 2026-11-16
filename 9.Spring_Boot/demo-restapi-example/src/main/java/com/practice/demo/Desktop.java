package com.practice.demo;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer {
    public void compile (){
        System.out.println("Compiling the code - desktop");
    }
}
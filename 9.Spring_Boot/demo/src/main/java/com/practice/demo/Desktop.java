package com.practice.demo;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class Desktop implements Computer {
    public void compile (){
        System.out.println("Compiling the code - desktop");
    }
}
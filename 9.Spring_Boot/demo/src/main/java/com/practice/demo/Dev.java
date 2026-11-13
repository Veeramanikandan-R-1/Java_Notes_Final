package com.practice.demo;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Dev{
    private Computer computer;
    
    @Autowired
    public void setComputer(Computer computer){
        this.computer = computer;
    };

    public void build(){
        computer.compile();
        System.out.println("building the project");
    };
}
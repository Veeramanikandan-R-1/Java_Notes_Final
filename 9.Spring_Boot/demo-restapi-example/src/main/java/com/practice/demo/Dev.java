package com.practice.demo;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class Dev{
    @Autowired
    @Qualifier("laptop")
    private Computer computer;
    
    // public void setComputer(Computer computer){
    //     this.computer = computer;
    // };

    public void build(){
        computer.compile();
        System.out.println("building the project");
    };
}
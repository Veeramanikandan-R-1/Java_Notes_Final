package com.spring.jdbc.practice.model;
import org.springframework.stereotype.Component;

@Component
public class Alien {
    private int id;
    private String name;
    private String tech;

    // add getters and setters for all above fields

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getTech(){
        return tech;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTech(String tech){
        this.tech = tech;
    }

}

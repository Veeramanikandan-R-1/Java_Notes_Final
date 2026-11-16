package com.example.spring_app_jdbc_h2db.model;

import org.springframework.stereotype.Component;

@Component 
public class Alien {
    private int id;
    private String name;
    private String tech;

    // genereate getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        return "Alien [id=" + id + ", name=" + name + ", tech=" + tech + "]";
    }
}

package com.springapp.demo;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final AppProperties properties;

    public AppService(AppProperties properties) {
        this.properties = properties;
    }

    public void printConfig() {
        System.out.println(properties.getName());
        System.out.println(properties.getHost());
        System.out.println(properties.getPort());
    }
}

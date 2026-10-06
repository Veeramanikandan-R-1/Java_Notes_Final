package com.springapp.demo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Component
@RequestScope
public class RequestData{
    private final String id = UUID.randomUUID().toString();

    public String getId(){
        return id;
    }
}
package com.springapp.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession{
    private String userName;
    
    public void setUserName(String name){
        this.userName = name;
    }

    public String getUserName(){
        return this.userName;
    }
}
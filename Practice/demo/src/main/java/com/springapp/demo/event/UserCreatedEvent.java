package com.springapp.demo.event;

public class UserCreatedEvent{
    private final Long userId;
    private final String username;

    public UserCreatedEvent(Long userId, String username){
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.username;
    }
}
package com.springapp.demo;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Scope("prototype")
public class UserService{

    @Autowired
    private UserRepository userRep;

    // @Autowired(required=false)
    // public void setUserRepository(UserRepository userRep){
    //     this.userRep = userRep;
    // }

    public void createUser(){
        userRep.save();
    }
}

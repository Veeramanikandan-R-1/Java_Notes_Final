package com.springapp.demo.controller;

import com.springapp.demo.dto.UserRequestDto;
import com.springapp.demo.dto.UserResponseDto;
import com.springapp.demo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/new")
public class UserController{
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request){
        System.out.println("test");
        return userService.createUser(request);
    }
}

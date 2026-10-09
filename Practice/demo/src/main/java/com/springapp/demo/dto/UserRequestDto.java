package com.springapp.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto{
    @NotBlank(message="Name is Required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message="Email is Required")
    private String email;

    @Size(min = 8, message = "Password must me min of 8 Chars")
    @NotBlank(message="Password is Required")
    private String password;

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
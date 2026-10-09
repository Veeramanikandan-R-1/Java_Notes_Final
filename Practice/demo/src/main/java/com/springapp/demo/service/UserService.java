package com.springapp.demo.service;

import com.springapp.demo.dto.UserRequestDto;
import com.springapp.demo.dto.UserResponseDto;
import com.springapp.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong idGenerator = new AtomicLong();

    public UserResponseDto createUser(UserRequestDto request){

        User user = new User(
            idGenerator.incrementAndGet(),
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );

        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}


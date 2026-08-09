package com.example.SpringDataJpaDemo.controller;

import com.example.SpringDataJpaDemo.service.UserService;
import dto.CreateUserDto;
import dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;

    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto){
        return userService.save
    }

}

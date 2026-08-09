package com.restDemo.restDemo.controller;

import com.restDemo.restDemo.dto.CreateUserDto;
import com.restDemo.restDemo.dto.UserDto;
import com.restDemo.restDemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public  ResponseEntity<List<UserDto>> getAllusers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));

    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUserDto updateUserDto, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(updateUserDto, id));

    }

    }



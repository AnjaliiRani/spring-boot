package com.restDemo.restDemo.service;

import com.restDemo.restDemo.dto.CreateUserDto;
import com.restDemo.restDemo.dto.UserDto;
import com.restDemo.restDemo.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserDto> getAllUsers(){
            return this.userRepository.findAll();
    }
    public UserDto getUserById(String id){
        return userRepository.findUserById(id);

    }

    public @Nullable UserDto createUser(CreateUserDto createUserDto) {
        return userRepository.save(createUserDto);
    }

    public @Nullable UserDto updateUser(CreateUserDto updateUserDto, String id) {
        if(userRepository.findUserById(id) == null){
            return null;
        }
        return userRepository.update(updateUserDto , id);
    }

    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }
}

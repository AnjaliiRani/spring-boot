package com.example.SpringDataJpaDemo.service;

import com.example.SpringDataJpaDemo.entities.User;
import com.example.SpringDataJpaDemo.repository.UserRepository;
import dto.CreateUserDto;
import dto.UserDto;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public @Nullable UserDto saveUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());

         User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }
}

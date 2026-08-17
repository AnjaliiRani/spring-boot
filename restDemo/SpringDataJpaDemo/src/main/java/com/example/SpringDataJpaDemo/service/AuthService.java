package com.example.SpringDataJpaDemo.service;

import com.example.SpringDataJpaDemo.entities.Role;
import com.example.SpringDataJpaDemo.entities.User;
import com.example.SpringDataJpaDemo.repository.UserRepository;
import dto.CreateUserDto;
import dto.LoginDto;
import dto.LoginResponseDto;
import dto.RegisterUserResponseDto;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterUserResponseDto registerUser(CreateUserDto createUserDto){
        User user= new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        user.setPassword( passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return new RegisterUserResponseDto(savedUser.getName(), savedUser.getId());
    }

    public @Nullable LoginResponseDto login(LoginDto loginDto) {
    }
}

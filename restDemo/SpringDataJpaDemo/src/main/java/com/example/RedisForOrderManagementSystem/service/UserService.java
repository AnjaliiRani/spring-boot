package com.example.RedisForOrderManagementSystem.service;

import com.example.RedisForOrderManagementSystem.entities.User;
import com.example.RedisForOrderManagementSystem.exception.UserNotFoundException;
import com.example.RedisForOrderManagementSystem.repository.UserRepository;
import com.example.RedisForOrderManagementSystem.dto.CreateUserDto;
import com.example.RedisForOrderManagementSystem.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.context.SecurityContextHolder;
    @Service
    @AllArgsConstructor
    public class UserService {

        private final UserRepository userRepository;

        private User getLoggedInUser() {
            String email = Objects.requireNonNull(SecurityContextHolder.getContext()
                            .getAuthentication())
                    .getName();

            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        }

        public UserDto getCurrentUser() {
            User user = getLoggedInUser();
            return map(user);
        }

        @Transactional
        public UserDto updateCurrentUser(CreateUserDto dto) {
            User user = getLoggedInUser();

            user.setName(dto.getName());
            user.setEmail(dto.getEmail());

            return map(user);
        }

        public List<UserDto> getUsers() {
            return userRepository.findAll().stream()
                    .map(this::map)
                    .toList();
        }

        public UserDto getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return map(user);
        }

        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

        private UserDto map(User user) {
            return new UserDto(user.getId(), user.getName(), user.getEmail());
        }
    }
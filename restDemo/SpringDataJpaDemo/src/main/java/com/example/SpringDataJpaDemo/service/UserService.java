package com.example.SpringDataJpaDemo.service;

import com.example.SpringDataJpaDemo.entities.User;
import com.example.SpringDataJpaDemo.repository.UserRepository;
import dto.CreateUserDto;
import dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserDto> getUsers() {
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user :users){
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public  UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserDto(user.getId(), user.getName(), user.getEmail());


    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
    @Transactional
    public UserDto updateUser(CreateUserDto updateUserDto, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        return new UserDto(user.getId(), user.getName(), user.getEmail());
// no save() used here
    }

    @Transactional
    public UserDto patchUser(Long id, CreateUserDto patchUserDto) {
        return null;
    }

    public @Nullable List<UserDto> getUsersPaginated(int page, int pageSize, String direction, String sortBy) {
    }
}



















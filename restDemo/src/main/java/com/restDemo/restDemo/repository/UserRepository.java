package com.restDemo.restDemo.repository;

import com.restDemo.restDemo.dto.CreateUserDto;
import com.restDemo.restDemo.dto.UserDto;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserRepository {
    List<UserDto> users = new ArrayList<>();

    public UserRepository() {
        users.add(new UserDto(UUID.randomUUID().toString(), "Anjali", "Anjali@gmail.com"));
        users.add(new UserDto(UUID.randomUUID().toString(), "Aman", "Aman@gmail.com"));
        users.add(new UserDto(UUID.randomUUID().toString(), "Anand", "Anand@gmail.com"));

    }

    public List<UserDto> findAll() {
        return this.users;
    }

    public UserDto findUserById(String id) {
        for (UserDto user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;

            }
        }
        return null;
    }

    public @Nullable UserDto save(CreateUserDto createUserDto) {

        UserDto user = new UserDto(UUID.randomUUID().toString(), createUserDto.getName(), createUserDto.getEmail());
        users.add(user);
        return user;
    }

    public @Nullable UserDto update(CreateUserDto updateUserDto, String id) {
        for(UserDto user: users){
            if(user.getId().equals(id)){
                user.setEmail(updateUserDto.getEmail());
                user.setName(updateUserDto.getName());
                return user;
            }

        }
        return null;
    }

    public void deleteUserById(String id) {
        users.removeIf( userDto -> userDto.getId().equals(id));
    }
}


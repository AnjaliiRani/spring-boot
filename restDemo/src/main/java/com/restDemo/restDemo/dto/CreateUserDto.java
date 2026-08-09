package com.restDemo.restDemo.dto;

public class CreateUserDto {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private String email;
}

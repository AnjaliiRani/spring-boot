package com.example.SpringDataJpaDemo.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMsg){
        super(errorMsg); //extended parent class runtime exception so this is constructor of Runtime

    }
}

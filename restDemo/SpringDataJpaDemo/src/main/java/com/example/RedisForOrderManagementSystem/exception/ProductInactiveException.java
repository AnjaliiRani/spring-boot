package com.example.RedisForOrderManagementSystem.exception;


    public class ProductInactiveException extends RuntimeException{
        public ProductInactiveException(String errorMsg){
            super(errorMsg); //extended parent class runtime exception so this is constructor of Runtime

        }
    }

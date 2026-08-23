package com.example.OrderManagementSystem.exception;


    public class ProductInactiveException extends RuntimeException{
        public ProductInactiveException(String errorMsg){
            super(errorMsg); //extended parent class runtime exception so this is constructor of Runtime

        }
    }

package com.example.SpringDataJpaDemo.exception;

import dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex){
 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto("USER_NOT_FOUND", ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException ex){
        HashMap<String,String>  fieldsErroesMap = new HashMap<>();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto
                ("INVALID_INPUT", ex.getBindingResult().getFieldError().toString()));
    }
}

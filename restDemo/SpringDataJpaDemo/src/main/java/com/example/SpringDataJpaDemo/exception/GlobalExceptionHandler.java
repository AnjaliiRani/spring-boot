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
        HashMap<String,String>  fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        } );
        StringBuilder errorMsg = new StringBuilder();
        for (String field: fieldErrors.keySet()){
            errorMsg.append(field).append(" : ").append(fieldErrors.get(field)).append("\n");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto
                ("INVALID_INPUT", errorMsg.toString()));
    }
}

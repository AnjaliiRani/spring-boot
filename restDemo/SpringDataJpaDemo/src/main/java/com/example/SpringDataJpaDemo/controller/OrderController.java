package com.example.SpringDataJpaDemo.controller;

import com.example.SpringDataJpaDemo.service.OrderService;
import dto.CreateOrderDto;
import dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userId}/orders") // orders associate with users if there will be no users then there will be bo orders so this is the standard way of writing that we are maintaining
public class OrderController {
    private  final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId , CreateOrderDto createOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId ,createOrderDto));
    }





}

package com.example.SpringDataJpaDemo.controller;

import com.example.SpringDataJpaDemo.service.OrderService;
import dto.CreateOrderDto;
import dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userId}/orders") // orders associate with users if there will be no users then there will be bo orders so this is the standard way of writing that we are maintaining
public class OrderController {
    private  final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId ,@RequestBody CreateOrderDto createOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId ,createOrderDto));
    }
   @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable Long userId){
         return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUserId(userId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        OrderService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();





}

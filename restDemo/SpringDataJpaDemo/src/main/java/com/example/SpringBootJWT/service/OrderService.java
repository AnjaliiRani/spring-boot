package com.example.SpringBootJWT.service;

import com.example.SpringBootJWT.entities.Order;
import com.example.SpringBootJWT.entities.User;
import com.example.SpringBootJWT.repository.OrderRepository;
import com.example.SpringBootJWT.repository.UserRepository;
import com.example.SpringBootJWT.dto.CreateOrderDto;
import com.example.SpringBootJWT.dto.OrderDto;
import com.example.SpringBootJWT.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
 @AllArgsConstructor

 public class OrderService {
     private final OrderRepository orderRepository;
     private final UserRepository userRepository;

             private User getLoggedInUser() {
                 String email = Objects.requireNonNull(SecurityContextHolder.getContext()
                              .getAuthentication())
                   .getName();

                 return userRepository.findByEmail(email)
                         .orElseThrow(() -> new RuntimeException("User not found"));
            }
          public OrderDto createOrder(CreateOrderDto dto) {

                 User user = getLoggedInUser();

                 Order order = new Order();
                 order.setUser(user);
                 order.setProductName(dto.getProductName());

                 Order saved = orderRepository.save(order);

                 return map(saved);
             }
                public List<OrderDto> getMyOrders() {

                 User user = getLoggedInUser();

                 return orderRepository.findByUserId(user.getId())
                         .stream()
                         .map(this::map)
                         .toList();
             }
                 public OrderDto getMyOrder(Long id) {
                User user = getLoggedInUser();

               Order order = orderRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Order not found"));

                 if (!order.getUser().getId().equals(user.getId())) {
                         throw new RuntimeException("Access denied");
                     }

                 return map(order);
             }

             public List<OrderDto> getAllOrders() {
                 return orderRepository.findAll()
                         .stream()
                         .map(this::map)
                         .toList();
             }

            public List<OrderDto> getOrdersByUser(Long userId) {
                 return orderRepository.findByUserId(userId)
                        .stream()
                         .map(this::map)
                        .toList();
             }

             public void deleteOrder(Long id) {
                 orderRepository.deleteById(id);
            }

             private OrderDto map(Order order) {
                 User u = order.getUser();
                   return new OrderDto(
                           order.getId(),
                               order.getProductName(),
                           new UserDto(u.getId(), u.getName(), u.getEmail())
                 );
             }
 }

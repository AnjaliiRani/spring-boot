package com.example.SpringDataJpaDemo.repository;

import com.example.SpringDataJpaDemo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}

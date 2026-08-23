package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository< Product, Long>{
    List<Product> findAllByActiveTrueOrderByNameAsc();
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);


}

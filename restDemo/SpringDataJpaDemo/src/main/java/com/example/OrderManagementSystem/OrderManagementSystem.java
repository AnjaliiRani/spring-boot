package com.example.OrderManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrderManagementSystem {

	public static void main(String[] args)
    {
		SpringApplication.run(OrderManagementSystem.class, args);
	}

}

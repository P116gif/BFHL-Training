package com.example.day6;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Override
    Order save(Order order);
}

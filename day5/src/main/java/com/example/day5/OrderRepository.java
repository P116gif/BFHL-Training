package com.example.day5;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Override
    Order save(Order order);
}

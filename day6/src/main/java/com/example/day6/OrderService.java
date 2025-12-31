package com.example.day6;


public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String product, int quantity) {
        
        return orderRepository.save(new Order(product, quantity));
    }
}

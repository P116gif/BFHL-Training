package com.example.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldSaveOrder() {

        when(orderRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Order order = orderService.createOrder("item1", 2);

        verify(orderRepository).save(order);
    }

}

package com.example.day6;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;


@Testcontainers
@SpringBootTest
public class OrderRepositoryIT {
    
    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:15")
    .withCommand(
        "postgres", "-c", "Timezone=UTC"
    );

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    OrderRepository orderRepository;

    @Test
    void shouldSaveOrder(){
        Order order = orderRepository.save(new Order("itemIT", 5));
        assertTrue(orderRepository.findById(order.getId()).isPresent());
    }
}

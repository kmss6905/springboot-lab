package com.example.springtestcode.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findByOrderId(){
        Integer orderId = 1;
        Order order = findOrder(orderId);
        assertThat(order).isEqualTo(new Order("hello", 1));
    }

    public Order findOrder(Integer orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

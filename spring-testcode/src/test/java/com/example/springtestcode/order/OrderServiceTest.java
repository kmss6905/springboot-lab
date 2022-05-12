package com.example.springtestcode.order;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    private OrderService orderService;

    @Test
    void getOrderByName(){
        // given
        Integer orderId = 2;

        // when
        Order order = orderService.getOrderById(orderId);

        // then
        assertThat(order).isEqualTo(new Order("hello", 2));
    }
}

package com.example.springtestcode.order;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

public class BookOrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrderByName(){
        // given
        Long orderId = 2L;

        // when
        BookOrder bookOrder = orderService.getOrderById(orderId);

        // then
        assertThat(bookOrder).isEqualTo(new BookOrder("hello", 2L));
    }
}

package com.example.springtestcode.order;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class BookOrderTest {

    @Test
    void getUserNameWhenUserNameIsNull(){
        BookOrder bookOrder = new BookOrder("hello");
        System.out.println(bookOrder);

        assertThatThrownBy(bookOrder::getUserName)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("order user name can't be null or blank");

    }
}

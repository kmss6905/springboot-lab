package com.example.springtestcode.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookOrderNumber {
    private Long orderId;

    public BookOrderNumber(Long orderId) {
        this.orderId = orderId;
    }
}

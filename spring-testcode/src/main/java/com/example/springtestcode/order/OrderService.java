package com.example.springtestcode.order;

public interface OrderService {
    BookOrder getOrderById(Long orderId);
    BookOrderDto createBookOrder(BookOrderDto bookOrderDto);
    Long deleteOrder(BookOrderNumber bookOrderNumber);
}

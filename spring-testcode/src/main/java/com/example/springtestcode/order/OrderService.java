package com.example.springtestcode.order;

public interface OrderService {
    BookOrder getOrderById(Long orderId);
    BookOrderDto createBookOrder(BookOrderDto bookOrderDto);
    BookOrderDto updateOrder(Long userId, BookOrderDto bookOrderDto);
    Long deleteOrder(BookOrderNumber bookOrderNumber);
}

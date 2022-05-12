package com.example.springtestcode.order;

public interface OrderRepository {
    Order findById(Integer orderId);
}

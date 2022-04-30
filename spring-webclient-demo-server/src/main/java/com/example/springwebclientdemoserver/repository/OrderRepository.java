package com.example.springwebclientdemoserver.repository;


import com.example.springwebclientdemoserver.dto.OrderDto;

import java.util.Optional;

public interface OrderRepository {
    Optional<OrderDto> getOrderByOrderId(int orderId);
}

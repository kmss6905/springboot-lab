package com.example.springwebclientdemoserver.repository;

import com.example.springwebclientdemoserver.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

    private final List<OrderDto> preOrders;

    @Override
    public Optional<OrderDto> getOrderByOrderId(int orderId) {
        return preOrders.stream()
                .filter(it -> it.getOrderId() == orderId)
                .findFirst();
    }
}

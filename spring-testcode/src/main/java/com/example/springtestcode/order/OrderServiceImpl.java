package com.example.springtestcode.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final BookOrderRepository bookOrderRepository;

    @Override
    public BookOrder getOrderById(Long orderId) {
        return findOrder(orderId);
    }

    public BookOrder findOrder(Long orderId){
        return bookOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

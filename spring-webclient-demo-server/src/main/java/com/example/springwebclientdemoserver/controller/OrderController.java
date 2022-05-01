package com.example.springwebclientdemoserver.controller;

import com.example.springwebclientdemoserver.dto.OrderDto;
import com.example.springwebclientdemoserver.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final List<OrderDto> preOrders;
    private final OrderRepository orderRepository;

    @GetMapping("/v1/orders")
    public List<OrderDto> getPreOrders(){
        return preOrders;
    }

    @GetMapping("/v1/orders/{orderId}")
    public OrderDto getOrder(@PathVariable int orderId){
        return orderRepository.getOrderByOrderId(orderId)
                .orElseThrow( () -> new RuntimeException("not fount order"));
    }
}

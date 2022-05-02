package com.example.springwebclientdemoserver.controller;

import com.example.springwebclientdemoserver.dto.OrderDto;
import com.example.springwebclientdemoserver.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final List<OrderDto> preOrders;
    private final OrderRepository orderRepository;

    @GetMapping("/v1/orders")
    public List<OrderDto> getPreOrders(){
        return preOrders;
    }

    @GetMapping("/v1/orders/{orderId}")
    public OrderDto getOrder(@PathVariable int orderId){
        log.debug("order Id : {}", orderId);
        return orderRepository.getOrderByOrderId(orderId)
                .orElseThrow( () -> new RuntimeException("not fount order"));
    }
}

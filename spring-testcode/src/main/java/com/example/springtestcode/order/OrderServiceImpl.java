package com.example.springtestcode.order;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BookOrder getOrderById(Long orderId) {
        return findOrder(orderId);
    }

    public BookOrder findOrder(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

package com.example.springtestcode.order;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return findOrder(orderId);
    }

    public Order findOrder(Integer orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

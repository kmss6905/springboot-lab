package com.example.springwebclientdemoserver.repository;

import com.example.springwebclientdemoserver.config.DbConfig;
import com.example.springwebclientdemoserver.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DbConfig.class, OrderRepositoryImpl.class})
class OrderRepositoryImplTest {
    @Autowired private OrderRepository orderRepository;

    @Test
    public void 주문번호로_주문가져오기(){
        // given
        int orderId = 1;
        OrderDto findOrder = orderRepository
                .getOrderByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        assertThat(findOrder.getOrderId()).isEqualTo(1);
    }

    @Test
    public void 주문번호_존재하지않는_주문조회_예외발생() throws RuntimeException{
        // given
        int orderId = 10;

        assertThatThrownBy(() -> {
            // when
            orderRepository
                    .getOrderByOrderId(orderId)
                    .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        }).isInstanceOf(RuntimeException.class) // then
                .hasMessageContaining("주문을 찾을 수 없습니다.");

    }
}
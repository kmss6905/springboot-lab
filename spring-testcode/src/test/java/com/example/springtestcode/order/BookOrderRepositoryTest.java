package com.example.springtestcode.order;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest
public class BookOrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Container
    private static final MariaDBContainer mariaDBContainer = new MariaDBContainer("mariadb:10.3")
            .withDatabaseName("test");


    @BeforeAll
    static void beforeAll() {
        mariaDBContainer.start();
        System.out.println("mariaDBContainer.getJdbcUrl() : " + mariaDBContainer.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
        mariaDBContainer.stop();
    }

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 성공")
    void findByOrderId() {
        BookOrder save = orderRepository.save(new BookOrder("hello", 123L));
        BookOrder bookOrder = findOrder(save.getId());
        assertThat(bookOrder.getId()).isEqualTo(save.getId());
    }

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 실패, 존재하지 않는 주문번호")
    void finNonExistentBookOrderByOrderId(){

        assertThatThrownBy(() -> findOrder(9999L), "존재하지 않는 주문 번호 검색 시 예외")
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found order");
    }

    public BookOrder findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

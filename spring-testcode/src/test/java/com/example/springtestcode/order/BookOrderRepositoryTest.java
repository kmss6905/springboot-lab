package com.example.springtestcode.order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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

//    @AfterAll
//    static void afterAll() {
////        mariaDBContainer.stop();
//    }

    @Test
    void openTestcontainer() {
        System.out.println("hello world");
    }


    @Test
    void findByOrderId() {
        BookOrder save = orderRepository.save(new BookOrder("hello", 123L));
        BookOrder bookOrder = findOrder(save.getId());
        assertThat(bookOrder.getId()).isEqualTo(save.getId());
    }
//
    public BookOrder findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

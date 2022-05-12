package com.example.springtestcode;

import com.example.springtestcode.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testName(){

        // when
        ResponseEntity<Order> order = restTemplate.getForEntity("/v1/orders/1", Order.class);

        // then
        assertThat(order.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(order.getBody().getGetOrderUserName()).isEqualTo("minshik");
        assertThat(order.getBody().getGetOrderId()).isEqualTo(1);
    }
}

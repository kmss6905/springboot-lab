package com.example.springtestcode.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOrderWhenGivenOrderId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/orders/{orderId}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("hello"))
                .andExpect(jsonPath("id").value(3));

    }
}

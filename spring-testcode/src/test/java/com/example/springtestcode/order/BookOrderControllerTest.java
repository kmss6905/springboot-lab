package com.example.springtestcode.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@DisplayName("책 주문 컨트롤러 테스트")
class BookOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("주문번호 id (Long) 로 책 주문 가져오기 - 성공")
    public void getOrderWhenGivenOrderId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/orders/{orderId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("hello"));
    }

    @Test
    @DisplayName("책 주문 생성하기 - 성공")
    public void createOrder() throws Exception{
        // when
        BookOrderDto bookOrderDto = new BookOrderDto(
            "홍길동전", "김민식", 10000L
        );
        String valueAsString = objectMapper.writeValueAsString(bookOrderDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/order")
                                .content(valueAsString)
                                .contentType("application/json"))
                .andExpect(status().isCreated());
    }
}

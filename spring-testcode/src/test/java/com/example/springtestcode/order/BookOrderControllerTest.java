package com.example.springtestcode.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@DisplayName("책 주문 컨트롤러 테스트")
class BookOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("주문번호 id (Long) 로 책 주문 가져오기 - 성공")
    public void getOrderWhenGivenOrderId() throws Exception{

        // given
        BookOrder bookOrder = new BookOrder("hello", "홍길동전");
        when(orderService.getOrderById(1L)).thenReturn(bookOrder);

        // then
        mockMvc.perform(get("/v1/orders/{orderId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("hello"))
                .andExpect(jsonPath("bookName").value("홍길동전"));
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

    @Test
    @DisplayName("책 주문 취소하기")
    public void deleteOrder() throws Exception{
        BookOrderNumber orderNumber = new BookOrderNumber(1L);
        String valueAsString = objectMapper.writeValueAsString(orderNumber);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/order")
                        .content(valueAsString)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문 내용 변경하기 - 관리자")
    public void updateOrder() throws Exception{
        BookOrderDto bookOrderDto = new BookOrderDto("hello minshik");
        String valueAsString = objectMapper.writeValueAsString(bookOrderDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/order/1")
                .content(valueAsString)
                .contentType("application/json")
        ).andExpect(status().isOk());
    }
}

package lab.springwebclient.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.springwebclient.OrderClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;


@SpringBootTest(classes = { OrderClient.class })
class OrderServiceImplTest {

    @Autowired private OrderClient orderClient;

    public static MockWebServer mockBackEnd;
    public OrderService orderService;
    public static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() throws IOException{
        objectMapper = new ObjectMapper();
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void initialize(){
        String mackBaseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        WebClient.Builder webClientBilder = WebClient.builder().baseUrl(mackBaseUrl);
        orderClient = new OrderClient(webClientBilder);
        orderService = new OrderServiceImpl(orderClient);
    }

    @Test
    @DisplayName("주문번호")
    void getOrderTest() {
        /**
         *  OrderService.class 의 getOrder(String orderId) 메서드로 부터 실제 API CALL 요청될때,
         *  MockWebServer 는 미리 만들어놓은 응답값을 리턴한다.
         */
        OrderDto orderDto = new OrderDto(1, "닌텐도", "흰둥이", "1000");

        JsonNode jsonNode = objectMapper
                .valueToTree(orderDto);

        // mockServer 를 이용하여 응답값을 스텁 한다.
        mockBackEnd.enqueue(new MockResponse()
                .setBody(jsonNode.toString())
                .addHeader("Content-Type", "application/json"));

        // 실제 API 콜이 나갈 때, mockserver의 스텁한 응답값을 리턴하게 된다.
        Mono<JsonNode> jsonNodeMono = orderService.getOrder("1");

        StepVerifier.create(jsonNodeMono)
                .expectNextMatches(order -> order.get("product_name").textValue().equals("닌텐도"))
                .verifyComplete();
    }

    @Test
    void getOrdersTest() {
    }

    @AfterAll
    static void tearDown() throws IOException{
        mockBackEnd.shutdown();
    }
}
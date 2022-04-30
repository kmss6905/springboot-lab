package lab.springwebclient;

import com.fasterxml.jackson.databind.JsonNode;
import lab.springwebclient.config.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderClient {
    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class);
    private final WebClient webClient;

    @Autowired
    public OrderClient(OrderProperties orderProperties) {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.USER_AGENT)
                .filter(logRequest())
                .build();
    }

    // 주문들 가져오기
    public Flux<JsonNode> getOrders() {
        return webClient.get()
                .uri("/v1/orders")
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(JsonNode.class);
                    }
                    else {
                        return response.createException().flatMapMany(Mono::error);
                    }
                });
    }

    // 주문번호가져오기
    public Mono<JsonNode> getOrder(String orderId) {
        return webClient.get()
                .uri("/v1/orders/{orderId}", orderId)
                .exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(JsonNode.class);
            } else {
                return response.createException().flatMap(Mono::error);
            }
        });
    }

    private ExchangeFilterFunction logRequest () {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

}

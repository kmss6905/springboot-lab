package lab.springwebclient.order;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<JsonNode> getOrder(String orderId);
    Flux<JsonNode> getOrders();
}

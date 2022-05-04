package lab.springwebclient.order;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/v1/main-orders/{orderId}")
    public Mono<JsonNode> getOrder(@PathVariable String orderId){
        return orderService.getOrder(orderId);
    }

    @GetMapping("/v1/main-orders")
    public Flux<JsonNode> getOrders(){
        return orderService.getOrders();
    }
}

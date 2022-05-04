package lab.springwebclient.order;

import com.fasterxml.jackson.databind.JsonNode;
import lab.springwebclient.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderClient orderClient;

    @Override
    public Mono<JsonNode> getOrder(String orderId) {
        return orderClient.getOrder(orderId);
    }

    @Override
    public Flux<JsonNode> getOrders() {
        return orderClient.getOrders();
    }
}

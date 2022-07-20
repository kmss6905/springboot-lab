package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final LogTrace trace;
    private final OrderServiceV4 orderService;

    @GetMapping("/v4/request")
    public String request(String itemId) {
        AbstractTemplate<String> abstractTemplate = createTemplate(itemId);
        return abstractTemplate.execute("OrderControllerV4.request()");
    }

    private AbstractTemplate<String> createTemplate(String itemId) {
        return new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
    }
}

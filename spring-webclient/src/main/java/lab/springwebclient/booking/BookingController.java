package lab.springwebclient.booking;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/v1/booking/{booking_id}")
    public Mono<JsonNode> getBooking(@PathVariable("booking_id") String bookingId, @RequestParam String token){
        return bookingService.getBooking(bookingId, token);
    }
}

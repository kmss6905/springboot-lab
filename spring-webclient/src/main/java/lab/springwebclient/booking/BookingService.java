package lab.springwebclient.booking;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingClient bookingClient;

    public Mono<JsonNode> getBooking(String itineraryId, String token){

        return bookingClient.getItinerary(itineraryId, token);
    }
}

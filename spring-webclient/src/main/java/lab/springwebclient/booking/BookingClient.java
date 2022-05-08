package lab.springwebclient.booking;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class BookingClient {

    private final WebClient webClient;

    public BookingClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://test.ean.com/v3")
                .defaultHeader("Authorization", generateExpediaKey())
                .defaultHeader("Customer-Ip", "127.0.0.1")
                .defaultHeader("User-Agent", "WAFFLE_API/0.0.1")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Accept-Encoding", "gzip")
                .build();
    }

    /* 익스피디아 예약조회 */
    public Mono<JsonNode> getItinerary(String itineraryId, String token){
        return webClient.get()
                .uri("/itineraries/{retrieve_id}?token={token}", itineraryId, token)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }


    public static String generateExpediaKey() {
        String apiKey = "apikey";
        String secret = "secret";
        Date date= new java.util.Date();
        Long timestamp = (date.getTime() / 1000);
        String signature = null;
        try {
            String toBeHashed = apiKey + secret + timestamp;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(toBeHashed.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            signature = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String authHeaderValue = "EAN APIKey=" + apiKey +  ",Signature=" + signature + ",timestamp=" + timestamp;
        return authHeaderValue;
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.assertj.core.api.Assertions.assertThat;

public class EventStreamTest {

    @Test
    void whenSubscribeToIt_thenShouldConsumeAll() {
        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        EndSubscriber<String> subscriber = new EndSubscriber<>();
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        // when
        assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);
//
        items.forEach(it -> publisher.submit(it));
//        publisher.submit();

        // publisher 를 명시적으로 닫아야 하나? 굳이 그럴 필요는 없다. 닫아야 complete 호출되는 것은 아님
        publisher.close();

        // then
        assertThat(subscriber.consumedElements)
                .containsExactlyElementsOf(items);
    }
}

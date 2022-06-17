import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;

public class CustomPublisher<T> implements Publisher<T> {

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
    }
}

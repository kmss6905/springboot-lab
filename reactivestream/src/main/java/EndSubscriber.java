import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

import static java.util.concurrent.Flow.*;

public class EndSubscriber<T> implements Subscriber<T> {
    private Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();

    // onSubscribe() 는 프로세싱 시작전에 호출 됨,
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        // Note that when we started the subscription in the onSubscribe() method and when we processed a message
        // we need to call the request() method on the Subscription
        // to signal that the current Subscriber is ready to consume more messages.
        subscription.request(1);
    }

    // 해당 메서드는 Publisher 가 새로운 메시지를 publish 할때마다 호출 됨
    @Override
    public void onNext(T item) {
        System.out.println("Got : " + item);
        consumedElements.add(item);

        // 현재 subscriber 에게 더 많은 메시지를 소비(consume)할 준비가 되었다고 시그널을 보내기 위해 request() 메서드를 호출할 필요성이 있음
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        // processing 과정 중 exception 이 발생되었을 경우
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}

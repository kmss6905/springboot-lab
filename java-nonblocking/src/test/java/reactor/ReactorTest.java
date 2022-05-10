package reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {

    @Test
    void monoTest(){
        List<Integer> elements = new ArrayList<>();
        Flux.just(1,2,3,4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
//                        if(onNextAmount % 2 == 0){
//                            s.request(2);
//                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        assertThat(elements).containsExactly(1, 2);
    }

    @Test
    void concurrencyTest(){
        List<Integer> elements = new ArrayList<>();
        Flux.just(1,2,3,4)
                .log()
                .map( i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(elements::add);
        assertThat(elements).containsExactly(2,4,6,8);
    }
}

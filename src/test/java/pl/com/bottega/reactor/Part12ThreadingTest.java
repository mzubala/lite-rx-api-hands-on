package pl.com.bottega.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class Part12ThreadingTest {

    private Part12Threading workshop = new Part12Threading();

    @Test
    public void subscribeOnScheduler() {
        var it = workshop.subscribeOnScheduler(
            Flux.range(0, 100).map(Object::toString).delayElements(Duration.ofMillis(10))
        ).log().toIterable();

        System.out.println(it);

        assertThat(it).doesNotContain(Thread.currentThread().getName());
    }

    @Test
    public void runsInParallel() {
        var it = workshop.runsInParallel(
            Flux.range(0, 100).map(Object::toString).delayElements(Duration.ofMillis(10))
        ).log().toIterable();

        System.out.println(it);

        assertThat(it).doesNotContain(Thread.currentThread().getName());
    }

}

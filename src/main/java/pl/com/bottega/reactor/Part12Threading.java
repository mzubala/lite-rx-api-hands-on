package pl.com.bottega.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Part12Threading {

    public Flux<String> subscribeOnScheduler(Flux<String> flux) {
        return flux.map((element) -> Thread.currentThread().getName())
            .subscribeOn(Schedulers.parallel());
    }

    public Flux<String> runsInParallel(Flux<String> flux) {
        return flux.parallel().map((element) -> Thread.currentThread().getName()).sequential();
    }

}

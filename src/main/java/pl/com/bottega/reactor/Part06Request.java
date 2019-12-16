package pl.com.bottega.reactor;

import pl.com.bottega.reactor.domain.User;
import pl.com.bottega.reactor.repository.ReactiveRepository;
import pl.com.bottega.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux, 0).thenRequest(Long.MAX_VALUE).expectNextCount(4).expectComplete();
    }

//========================================================================================

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux, 0)
            .thenRequest(1).expectNext(User.SKYLER).thenRequest(1)
            .expectNext(User.JESSE)
            .thenCancel();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return repository.findAll().log();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return repository.findAll()
            .doOnSubscribe((subscription) -> {
                System.out.println("Starring");
            }).doOnNext((element) -> {
                System.out.println(element.getFirstname() + " " + element.getLastname());
            }).doOnComplete(() -> {
                System.out.println("The end!");
            });
    }

}

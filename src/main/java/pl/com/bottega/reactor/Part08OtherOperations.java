package pl.com.bottega.reactor;

import org.assertj.core.groups.Tuple;
import pl.com.bottega.reactor.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	// TODO Create a Flux of user from Flux of username, firstname and lastname.
	Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
		/*Flux<Tuple2<String, String>> userNameWithFirstName = usernameFlux.zipWith(firstnameFlux);
		Flux<User> userFlux = userNameWithFirstName
			.zipWith(lastnameFlux, (Tuple2<String, String> userNameFirstName, String lastName)
				-> new User(userNameFirstName.getT1(), userNameFirstName.getT2(), lastName));
			return userFlux;*/
			return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
				.map((tuple) -> new User(tuple.getT1(), tuple.getT2(), tuple.getT3()));
	}

//========================================================================================

	// TODO Return the mono which returns its value faster
	Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
		return Mono.first(mono1, mono2);
	}

//========================================================================================

	// TODO Return the flux which returns the first value faster
	Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
		return Flux.first(flux1, flux2);
	}

//========================================================================================

	// TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
	Mono<Void> fluxCompletion(Flux<User> flux) {
		return flux.then();
	}

//========================================================================================

	// TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
	Mono<User> nullAwareUserToMono(User user) {
		return Mono.justOrEmpty(user);
	}

//========================================================================================

	// TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
	Mono<User> emptyToSkyler(Mono<User> mono) {
		return mono.defaultIfEmpty(User.SKYLER);
	}

}

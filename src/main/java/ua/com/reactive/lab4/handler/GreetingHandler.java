package ua.com.reactive.lab4.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Reader; // Використовуємо Reader
import ua.com.reactive.lab4.entity.Greeting;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Welcome to the world of books!")));
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Welcome to the main library page!"));
    }

    public Mono<ServerResponse> getClients(ServerRequest request) {
        String start = request
                .queryParam("start")
                .orElse("0");

        // Використовуємо Reader з повним конструктором
        Flux<Reader> readers = Flux.just(
                        new Reader(1L, "Andriy", "Kovalenko", "0671234567", "a.kovalenko@lib.ua", 0),
                        new Reader(2L, "Olena", "Shevchenko", "0997654321", "o.shevchenko@lib.ua", 1),
                        new Reader(3L, "Maksym", "Tkachenko", "0635554433", "m.tkachenko@lib.ua", 0)
                )
                .skip(Long.valueOf(start))
                .take(2);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(readers, Reader.class);
    }
}
package ua.com.reactive.lab5.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab5.entity.User; // <-- Використовуємо User

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Ласкаво просимо до електронної бібліотеки!"));
    }

    public Mono<ServerResponse> users(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Вітаємо, доступ до сторінки користувача відкрито!"));
    }

    public Mono<ServerResponse> admin(ServerRequest request) {

        Flux<User> users = Flux.
                just(
                        new User(1L, "Pavlo", "1234"),
                        new User(2L, "Maryna", "1234"),
                        new User(3L, "Oksana", "1234")
                );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users, User.class);
    }

}
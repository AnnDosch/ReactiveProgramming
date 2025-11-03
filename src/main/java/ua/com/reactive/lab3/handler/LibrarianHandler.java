package ua.com.reactive.lab3.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab3.entity.Librarian;

@Component
public class LibrarianHandler {

    public Mono<ServerResponse> getAllLibrarians(ServerRequest request) {

        Flux<Librarian> librarians = Flux.just(
    
                new Librarian(10L, "Oksana", "Lytvyn", "Senior Librarian"),
                new Librarian(11L, "Ihor", "Kravchenko", "Junior Assistant"),
                new Librarian(12L, "Natalija", "Sydorenko", "Chief Librarian")
        );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(librarians, Librarian.class);
    }
}

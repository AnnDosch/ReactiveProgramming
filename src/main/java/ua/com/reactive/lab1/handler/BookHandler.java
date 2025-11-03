package ua.com.reactive.lab1.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab1.entity.Book;
import java.time.LocalDate;

@Component
public class BookHandler {

    // Імітація бази даних Book з 9 аргументами
    private static final Flux<Book> BOOK_DB = Flux.just(
            new Book(101L, "The Hunger Games", "Sjuzanna Kollinz", "978-617-7585-05-0", 2008, 1001L, true, "Subscription", null),
            new Book(102L, "Harry Potter and the Philosopher's Stone", "Dž. K. Rouling", "978-0-7475-3274-3", 1997, 1002L, false, "Reading Room", LocalDate.of(2026, 1, 15)),
            new Book(103L, "Murder on the Orient Express", "Agata Kristi", "978-0062073499", 1934, 1003L, true, "Subscription", null)
    );

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BOOK_DB, Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        Long bookId = Long.valueOf(request.pathVariable("id"));
        Mono<Book> bookMono = BOOK_DB
                .filter(book -> book.getId().equals(bookId))
                .next();
        return bookMono
                .flatMap(book -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(book)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addBook(ServerRequest request) {
        Mono<Book> bookToSave = request.bodyToMono(Book.class);
        return bookToSave
                .flatMap(book -> {
                    book.setId(System.currentTimeMillis());
                    return ServerResponse
                            .status(201)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(book));
                });
    }
}
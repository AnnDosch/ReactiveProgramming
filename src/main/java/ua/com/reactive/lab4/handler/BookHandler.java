package ua.com.reactive.lab4.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Book;
import ua.com.reactive.lab4.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@RequiredArgsConstructor
public class BookHandler {

    // Вбудовуємо сервіс R2DBC
    private final BookService bookService;

    // GET /api/books (READ ALL)
    public Mono<ServerResponse> findAll(ServerRequest request) {
        // Використовуємо БД через сервіс
        Flux<Book> books = bookService.findAll();

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    // GET /api/books/{id} (READ BY ID)
    public Mono<ServerResponse> findById(ServerRequest request) {
        Long bookId = Long.valueOf(request.pathVariable("id"));

        Mono<Book> bookMono = bookService.findById(bookId);

        return bookMono
                .flatMap(book -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(book)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // POST /api/books (CREATE)
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Book> bookMono = request.bodyToMono(Book.class)
                .flatMap(book -> {
                    book.setId(null); // Забезпечуємо генерацію ID базою даних
                    return bookService.save(book);
                });

        return ServerResponse
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookMono, Book.class);
    }
}
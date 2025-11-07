package ua.com.reactive.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Book;
import ua.com.reactive.lab4.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // GET /api/v1/books (READ ALL)
    @GetMapping
    public Flux<Book> findAll() {
        return bookService.findAll();
    }

    // GET /api/v1/books/{id} (READ BY ID)
    @GetMapping("/{id}")
    public Mono<Book> findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // POST /api/v1/books (CREATE)
    @PostMapping
    public Mono<Book> create(@RequestBody Book book) {
        book.setId(null);
        return bookService.save(book);
    }

    // PUT /api/v1/books/{id} (UPDATE)
    @PutMapping("/{id}")
    public Mono<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.findById(id)
                .map(existingBook -> {
                    // Оновлення всіх полів (використовуємо Lombok setters)
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setPublicationYear(book.getPublicationYear());
                    existingBook.setInventoryNumber(book.getInventoryNumber());
                    existingBook.setIsAvailable(book.isAvailable());
                    existingBook.setLocation(book.getLocation());
                    existingBook.setCurrentReaderId(book.getCurrentReaderId());
                    existingBook.setDueDate(book.getDueDate());

                    return existingBook;
                })
                .flatMap(bookService::save);
    }

    // DELETE /api/v1/books/{id} (DELETE)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }
}
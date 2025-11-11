package ua.com.reactive.lab5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab5.entity.Book;
import ua.com.reactive.lab5.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Mono<Book> save(Book book) { return bookRepository.save(book); }
    public Flux<Book> findAll() { return bookRepository.findAll(); }
    public Mono<Book> findById(Long id) { return bookRepository.findById(id); }
    public Mono<Void> deleteById(Long id) { return bookRepository.deleteById(id); }
}
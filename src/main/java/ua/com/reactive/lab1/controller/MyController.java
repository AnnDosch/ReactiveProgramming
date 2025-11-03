package ua.com.reactive.lab1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.com.reactive.lab1.entity.Reader; // Використовуємо Reader
import ua.com.reactive.lab1.entity.Book;
import java.time.LocalDate; // <-- ДОДАЙТЕ ЦЕЙ ІМПОРТ

@RestController
public class MyController{

    @GetMapping("/clients")
    public Flux<Reader> getClients() {
        // Використовуємо Reader
        Flux<Reader> readers = Flux.just(
                        new Reader(1L, "Andriy", "Kovalenko", "0671234567", "a.kovalenko@lib.ua", 0),
                        new Reader(2L, "Olena", "Shevchenko", "0997654321", "o.shevchenko@lib.ua", 1),
                        new Reader(3L, "Maksym", "Tkachenko", "0635554433", "m.tkachenko@lib.ua", 0)
                )
                .skip(0)
                .take(2);

        return readers;
    }

    @GetMapping("/books")
    public Flux<Book> getBooks() {
        // Використовуємо Book з 9 аргументами
        return Flux.just(
                new Book(101L, "The Hunger Games", "Sjuzanna Kollinz", "978-617-7585-05-0", 2008, 1001L, true, "Subscription", null),
                new Book(102L, "Harry Potter and the Philosopher's Stone", "Dž. K. Rouling", "978-0-7475-3274-3", 1997, 1002L, false, "Reading Room", LocalDate.now().plusDays(7)),
                new Book(103L, "Murder on the Orient Express", "Agata Kristi", "978-0062073499", 1934, 1003L, true, "Subscription", null),
                new Book(104L, "The Ballad of Songbirds and Snakes", "Sjuzanna Kollinz", "978-1-338-77995-1", 2020, 1004L, true, "Subscription", null)
        );
    }
}
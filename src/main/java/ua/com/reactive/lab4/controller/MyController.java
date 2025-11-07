package ua.com.reactive.lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.com.reactive.lab4.entity.Book;
import ua.com.reactive.lab4.entity.Reader;
import java.time.LocalDate;

@RestController
public class MyController { // Не додаємо @RequestMapping тут

    // Маршрут з ЛР2, який використовує фіктивні дані Book
    @GetMapping("/books-mock")
    public Flux<Book> getBooksMock() {
        // Використовуємо явне приведення типу (LocalDate) null для уникнення помилок конструктора
        return Flux.just(
                new Book(101L, "The Hunger Games", "Sjuzanna Kollinz", "978-617-7585-05-0", 2008, 1001L, true, "Subscription", null, (LocalDate) null),
                new Book(102L, "Harry Potter", "Dž. K. Rouling", "978-0-7475-3274-3", 1997, 1002L, false, "Reading Room", null, LocalDate.now().plusDays(7)),
                new Book(103L, "Murder on the Orient Express", "Agata Kristi", "978-0062073499", 1934, 1003L, true, "Subscription", null, (LocalDate) null)
        );
    }

    // Маршрут з ЛР2, який використовує фіктивні дані Reader
    @GetMapping("/clients-mock")
    public Flux<Reader> getReadersMock() {
        return Flux.just(
                new Reader(1L, "Andriy", "Kovalenko", "0671234567", "a.kovalenko@lib.ua", 0),
                new Reader(2L, "Olena", "Shevchenko", "0997654321", "o.shevchenko@lib.ua", 1)
        );
    }
}
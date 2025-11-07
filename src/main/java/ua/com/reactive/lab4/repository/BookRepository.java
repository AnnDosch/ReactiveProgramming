package ua.com.reactive.lab4.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.lab4.entity.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}
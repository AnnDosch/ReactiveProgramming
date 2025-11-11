package ua.com.reactive.lab5.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.lab5.entity.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}
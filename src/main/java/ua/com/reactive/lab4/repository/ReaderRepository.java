package ua.com.reactive.lab4.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.lab4.entity.Reader;

public interface ReaderRepository extends ReactiveCrudRepository<Reader, Long> {
}
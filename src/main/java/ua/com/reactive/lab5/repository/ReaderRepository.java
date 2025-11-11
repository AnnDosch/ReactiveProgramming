package ua.com.reactive.lab5.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.lab5.entity.Reader;

public interface ReaderRepository extends ReactiveCrudRepository<Reader, Long> {
}
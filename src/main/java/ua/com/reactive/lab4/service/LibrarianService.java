package ua.com.reactive.lab4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Librarian;
import ua.com.reactive.lab4.repository.LibrarianRepository;

@Service
@RequiredArgsConstructor // Використовуємо стандартну ін'єкцію Spring Boot 3.x
public class LibrarianService {

    private final LibrarianRepository librarianRepository; // Final поле для ін'єкції

    public Mono<Librarian> save(Librarian librarian) {
        return librarianRepository.save(librarian); // CREATE / UPDATE
    }

    public Flux<Librarian> findAll() {
        return librarianRepository.findAll(); // READ ALL
    }

    public Mono<Librarian> findById(Long id) {
        return librarianRepository.findById(id); // READ BY ID
    }

    public Mono<Void> deleteById(Long id) {
        return librarianRepository.deleteById(id); // DELETE
    }
}
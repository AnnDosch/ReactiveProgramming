package ua.com.reactive.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Librarian;
import ua.com.reactive.lab4.service.LibrarianService;

@RestController
@RequestMapping("/api/v1/librarians")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    // GET /api/v1/librarians (READ ALL)
    @GetMapping
    public Flux<Librarian> findAll() {
        return librarianService.findAll();
    }

    // GET /api/v1/librarians/{id} (READ BY ID)
    @GetMapping("/{id}")
    public Mono<Librarian> findById(@PathVariable Long id) {
        return librarianService.findById(id);
    }

    // POST /api/v1/librarians (CREATE)
    @PostMapping
    public Mono<Librarian> create(@RequestBody Librarian librarian) {
        librarian.setId(null);
        return librarianService.save(librarian);
    }

    // DELETE /api/v1/librarians/{id} (DELETE)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return librarianService.deleteById(id);
    }
}
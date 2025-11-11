package ua.com.reactive.lab5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab5.entity.Reader;
import ua.com.reactive.lab5.service.ReaderService;

@RestController
@RequestMapping("/api/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    // GET /api/v1/readers (READ ALL)
    @GetMapping
    public Flux<Reader> findAll() {
        return readerService.findAll();
    }

    // GET /api/v1/readers/{id} (READ BY ID)
    @GetMapping("/{id}")
    public Mono<Reader> findById(@PathVariable Long id) {
        return readerService.findById(id);
    }

    // POST /api/v1/readers (CREATE)
    @PostMapping
    public Mono<Reader> create(@RequestBody Reader reader) {
        reader.setId(null); // Забезпечуємо генерацію ID
        return readerService.save(reader);
    }

    // PUT /api/v1/readers/{id} (UPDATE)
    @PutMapping("/{id}")
    public Mono<Reader> update(@PathVariable Long id, @RequestBody Reader reader) {
        // Оновлення полів за допомогою R2DBC
        return readerService.findById(id)
                .map(existingReader -> {
                    existingReader.setFirstName(reader.getFirstName());
                    existingReader.setLastName(reader.getLastName());
                    existingReader.setPhoneNumber(reader.getPhoneNumber());
                    existingReader.setEmail(reader.getEmail());
                    existingReader.setActiveOrdersCount(reader.getActiveOrdersCount());
                    return existingReader;
                })
                .flatMap(readerService::save)
                .switchIfEmpty(Mono.error(new RuntimeException("Reader not found")));
    }

    // DELETE /api/v1/readers/{id} (DELETE)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return readerService.deleteById(id);
    }
}
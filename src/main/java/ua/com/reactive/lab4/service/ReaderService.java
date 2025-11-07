package ua.com.reactive.lab4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab4.entity.Reader;
import ua.com.reactive.lab4.repository.ReaderRepository;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Mono<Reader> save(Reader reader) { return readerRepository.save(reader); }
    public Flux<Reader> findAll() { return readerRepository.findAll(); }
    public Mono<Reader> findById(Long id) { return readerRepository.findById(id); }
    public Mono<Void> deleteById(Long id) { return readerRepository.deleteById(id); }
}
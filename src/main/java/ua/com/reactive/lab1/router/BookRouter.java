package ua.com.reactive.lab1.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ua.com.reactive.lab1.handler.BookHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandler) {

        return RouterFunctions
                .route(GET("/api/books").and(accept(MediaType.APPLICATION_JSON)), bookHandler::getAllBooks)
                .andRoute(GET("/api/books/{id}").and(accept(MediaType.APPLICATION_JSON)), bookHandler::getBookById)
                .andRoute(POST("/api/books").and(contentType(MediaType.APPLICATION_JSON)), bookHandler::addBook);
    }
}
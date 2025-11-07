package ua.com.reactive.lab4.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ua.com.reactive.lab4.handler.BookHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandler) {

        return RouterFunctions
                // GET /api/books (READ ALL)
                .route(RequestPredicates.GET("/api/books").and(accept(MediaType.APPLICATION_JSON)), bookHandler::findAll)
                // GET /api/books/{id} (READ BY ID)
                .andRoute(RequestPredicates.GET("/api/books/{id}").and(accept(MediaType.APPLICATION_JSON)), bookHandler::findById)
                // POST /api/books (CREATE)
                .andRoute(RequestPredicates.POST("/api/books").and(accept(MediaType.APPLICATION_JSON)), bookHandler::create);
    }
}
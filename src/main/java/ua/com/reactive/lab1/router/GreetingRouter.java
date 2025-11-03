package ua.com.reactive.lab1.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ua.com.reactive.lab1.handler.GreetingHandler;
import ua.com.reactive.lab1.handler.LibrarianHandler; // <--- ІМПОРТ

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(
            GreetingHandler greetingHandler,
            LibrarianHandler librarianHandler // <--- ДОДАНО ЯК ЗАЛЕЖНІСТЬ
    ) {

        return RouterFunctions
                .route(RequestPredicates.GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello)
                .andRoute(RequestPredicates.GET("/"), greetingHandler::home)
                .andRoute(RequestPredicates.GET("/users"), greetingHandler::getClients)
                .andRoute(RequestPredicates.GET("/api/librarians"), librarianHandler::getAllLibrarians); // <--- НОВИЙ МАРШРУТ
    }
}
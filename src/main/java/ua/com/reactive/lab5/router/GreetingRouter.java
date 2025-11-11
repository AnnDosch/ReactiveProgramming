package ua.com.reactive.lab5.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ua.com.reactive.lab5.handler.GreetingHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {


    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RequestPredicate acceptJson = accept(MediaType.APPLICATION_JSON);

        return RouterFunctions
                // Маршрут без захисту (доступний всім, як налаштовано у Security)
                .route(RequestPredicates.GET("/"), greetingHandler::hello)

                // Маршрут для користувачів (вимагає ROLE_User)
                .andRoute(RequestPredicates.GET("/users").and(acceptJson), greetingHandler::users)

                // Маршрут для адміністраторів (вимагає ROLE_Admin)
                .andRoute(RequestPredicates.GET("/admin").and(acceptJson), greetingHandler::admin);

    }
}
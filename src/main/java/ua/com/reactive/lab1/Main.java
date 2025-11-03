package ua.com.reactive.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// @SpringBootApplication об'єднує @Configuration, @EnableAutoConfiguration та @ComponentScan.
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Запуск Spring Boot застосунку
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        // Тут можна додати логіку після запуску, якщо потрібно
        // (наприклад, для демонстрації, що контекст завантажено)
        System.out.println("Spring Reactive Application Started Successfully!");
    }
}
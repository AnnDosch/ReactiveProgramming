package ua.com.reactive.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    private Long id;                // ID читача
    private String firstName;       // Ім'я
    private String lastName;        // Прізвище
    private String phoneNumber;     // Контактний телефон
    private String email;           // Електронна пошта
    private int activeOrdersCount;  // Кількість активних замовлень
}
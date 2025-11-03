package ua.com.reactive.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Librarian {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;        // Посада
}
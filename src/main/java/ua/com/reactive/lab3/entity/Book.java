package ua.com.reactive.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;            // ISBN
    private int publicationYear;

    private Long inventoryNumber;   // Інвентарний номер
    private boolean isAvailable;
    private String location;        // Місце зберігання
    private LocalDate dueDate;      // Дата повернення
}
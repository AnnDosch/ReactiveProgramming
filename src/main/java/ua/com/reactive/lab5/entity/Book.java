package ua.com.reactive.lab5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("book")
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String isbn;
    @Column("publication_year")
    private int publicationYear;
    @Column("inventory_number")
    private Long inventoryNumber;
    @Column("is_available")
    private boolean isAvailable;
    private String location;
    @Column("current_reader_id")
    private Long currentReaderId;
    @Column("due_date")
    private LocalDate dueDate;

    public void setIsAvailable(boolean available) {
    }
}
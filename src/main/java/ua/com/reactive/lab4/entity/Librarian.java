package ua.com.reactive.lab4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("librarian")
public class Librarian {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
}
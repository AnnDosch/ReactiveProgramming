package ua.com.reactive.lab4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column; // <-- ДОДАНО
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("reader")
public class Reader {
    @Id
    private Long id;
    @Column("first_name") // Мапінг
    private String firstName;
    @Column("last_name") // Мапінг
    private String lastName;
    @Column("phone_number") // Мапінг
    private String phoneNumber;
    private String email;
    @Column("active_orders_count") // Мапінг
    private int activeOrdersCount;
}
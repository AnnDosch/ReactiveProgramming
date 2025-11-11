package ua.com.reactive.lab5.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("roles")
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    private String name;

    // Це поле потрібне для R2DBC/Spring, але ми його ігноруємо в R2DBC-логіці
    private Set<RolesHasUsers> rolesHasUsersList = new HashSet<>();

    @Override
    public String getAuthority() {
        return getName();
    }
}
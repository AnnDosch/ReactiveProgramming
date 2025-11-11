package ua.com.reactive.lab5.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table("users")
public class User implements UserDetails {

    @Id
    private Long id;
    private String username;
    private String password;

    // Це поле не повинно бути в конструкторі R2DBC!
    private Set<GrantedAuthority> authorities = new HashSet<>();

    // 1. Конструктор, який R2DBC використовує для читання з БД (id, username, password)
    // R2DBC автоматично заповнить поля БД, ігноруючи authorities.
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // 2. Конструктор для створення НОВОГО користувача (без id)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Булеві поля, які дозволяють вхід (залишаємо TRUE)
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
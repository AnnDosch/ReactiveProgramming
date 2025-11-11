package ua.com.reactive.lab5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.lab5.entity.Role;
import ua.com.reactive.lab5.entity.User; // Має бути ua.com.reactive.lab5.entity.User
import ua.com.reactive.lab5.repository.RoleRepository;
import ua.com.reactive.lab5.repository.RolesHasUsersRepository;
import ua.com.reactive.lab5.repository.UserRepository;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RolesHasUsersRepository rolesHasUsersRepository;
    private final RoleRepository roleRepository;

    public Flux<GrantedAuthority> getAuthorities(Long userId) {
        return rolesHasUsersRepository.findRoleIdsByUserId(userId)
                // Отримуємо об'єкти Role
                .flatMap(roleId -> roleRepository.findById(roleId))
                // Cast не потрібен, оскільки Role вже implements GrantedAuthority
                .cast(GrantedAuthority.class);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // 1. Знаходимо користувача за username (Mono<User>)
        return userRepository.findByUsername(username)
                .flatMap(user ->
                        // 2. Отримуємо його ролі (Flux<GrantedAuthority>)
                        getAuthorities(user.getId())
                                .collectList() // Збираємо ролі у List
                                .map(authoritiesList -> {
                                    // 3. Встановлюємо ролі в об'єкт User
                                    // ВИПРАВЛЕНО: Використовуємо метод setAuthorities з User.java
                                    user.setAuthorities(new HashSet<>(authoritiesList));
                                    return user; // Повертаємо об'єкт User
                                })
                )
                // 4. Повертаємо User як UserDetails
                .cast(UserDetails.class);
    }
}
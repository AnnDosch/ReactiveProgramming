package ua.com.reactive.lab5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder; // Не використовуйте в реальному проєкті!
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ua.com.reactive.lab5.service.UserService; // <-- ВАШ СЕРВІС

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity // Дозволяє використовувати @PreAuthorize
public class WebSecurityConfig {

    private final UserService userDetailsService;

    // Spring автоматично впроваджує UserService
    public WebSecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 1. PasswordEncoder (Використовуємо NoOpPasswordEncoder для паролів "1111", "2222")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // 2. Authentication Manager (Вказуємо, який UserDetailsService та PasswordEncoder використовувати)
    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder());
        return authManager;
    }

    // 3. Фільтр Безпеки (Визначає правила доступу до маршрутів)
    // У WebSecurityConfig.java
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/", "/registration")
                                .permitAll()
                                .pathMatchers("/users").hasRole("User")
                                .pathMatchers("/admin").hasRole("Admin")
                                .anyExchange().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(withDefaults())
                .authenticationManager(authenticationManager())
                .build();
    }}
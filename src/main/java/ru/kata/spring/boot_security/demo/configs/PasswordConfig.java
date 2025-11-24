package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Используем NoOpPasswordEncoder для хранения паролей в открытом виде
        return NoOpPasswordEncoder.getInstance();
        // return new BCryptPasswordEncoder(); // Закомментируйте эту строку
    }
}
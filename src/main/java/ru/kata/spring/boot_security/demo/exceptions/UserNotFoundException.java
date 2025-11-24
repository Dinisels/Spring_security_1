package ru.kata.spring.boot_security.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) { // Изменено с int на Long
        super("User not found with id: " + userId);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

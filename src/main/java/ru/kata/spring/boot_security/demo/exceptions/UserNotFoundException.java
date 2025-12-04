package ru.kata.spring.boot_security.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

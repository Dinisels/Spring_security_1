package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    Optional<User> getUserById(Long userId); // Этот метод должен быть объявлен с Long
    void deleteUser(User user);
    void deleteUserById(Long id);
    User findByEmail(String email);
}

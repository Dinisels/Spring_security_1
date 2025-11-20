package ru.kata.spring.boot_security.demo.dao;




import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    Optional<User> getUserById(int userId);
    void deleteUser(User user);
}
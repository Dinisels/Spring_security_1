package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    User getUserById(int userId);
    void deleteUser(int userId);
}

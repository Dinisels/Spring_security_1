package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.exceptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        // Шифруем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.createUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        // Получаем существующего пользователя для проверки пароля
        User existingUser = getUserById(user.getId());

        // Если пароль не изменился, оставляем старый (зашифрованный)
        if (user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getPassword().equals(existingUser.getPassword())) {
            user.setPassword(existingUser.getPassword());
        } else {
            // Если пароль изменился, шифруем новый
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Сохраняем роли из существующего пользователя, если они не установлены
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(existingUser.getRoles());
        }

        userDAO.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userDAO.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userDAO.deleteUser(user);
    }

    // ДОБАВЛЕН НОВЫЙ МЕТОД - для UserDetailsService
    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}

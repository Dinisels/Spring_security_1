package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Создаем роли, если их нет
        Role adminRole = createRoleIfNotExists("ROLE_ADMIN", "Administrator role");
        Role userRole = createRoleIfNotExists("ROLE_USER", "User role");

        // Создаем пользователей, если их нет
        createUserIfNotExists("admin@example.com", "Admin", 30, "admin",
                Set.of(adminRole, userRole));
        createUserIfNotExists("user@example.com", "User", 25, "user",
                Set.of(userRole));
    }

    private Role createRoleIfNotExists(String roleName, String description) {
        Role role = roleService.getRoleByName(roleName);
        if (role == null) {
            role = new Role(roleName, description);
            roleService.addRole(role);
        }
        return role;
    }

    private void createUserIfNotExists(String email, String name, int age, String password, Set<Role> roles) {
        User user = userService.findByEmail(email);
        if (user == null) {
            user = new User(name, age, email, password); // Без шифрования
            user.setRoles(roles);
            userService.createUser(user);
        }
    }
}

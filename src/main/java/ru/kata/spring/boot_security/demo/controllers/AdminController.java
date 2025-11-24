package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService; // ДОБАВЛЕНО

    @Autowired
    public AdminController(UserService userService, RoleService roleService) { // ДОБАВЛЕНО RoleService
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User()); // ДОБАВЬТЕ ЭТУ СТРОЧКУ
        return "admin";
    }

    @GetMapping("/new") // Изменено с "/addNewUser" на "/new"
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles()); // ДОБАВЛЕНО для выбора ролей
        return "user-form"; // Изменено на "user-form"
    }

    @PostMapping // Изменено с "/createUser" на корневой POST
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin"; // Изменено на "/admin"
    }

    @GetMapping("/{id}/edit") // Изменено на RESTful стиль
    public String updateUserForm(@PathVariable("id") Long id, Model model) { // Изменено с int на Long
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getRoles()); // ДОБАВЛЕНО для выбора ролей
        return "user-form"; // Изменено на "user-form"
    }

    @PatchMapping("/{id}") // Изменено на RESTful стиль с PATCH
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) { // Изменено с int на Long
        user.setId(id); // Устанавливаем ID из пути
        userService.updateUser(user);
        return "redirect:/admin"; // Изменено на "/admin"
    }

    @DeleteMapping("/{id}") // Изменено на RESTful стиль с DELETE
    public String deleteUser(@PathVariable("id") Long id) { // Изменено с int на Long
        userService.deleteUser(id);
        return "redirect:/admin"; // Изменено на "/admin"
    }
}

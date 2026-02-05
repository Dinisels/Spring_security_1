package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles());
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getRoles());
        return "user-form";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex) {
            return ResponseEntity.status(500)
                    .body("Произошла ошибка: " + ex.getMessage());
        }
    }
}

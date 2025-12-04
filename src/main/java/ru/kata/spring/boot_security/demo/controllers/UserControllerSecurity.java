package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControllerSecurity {

    @GetMapping
    public String userInfoSelf(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        return "user-form";
    }
}

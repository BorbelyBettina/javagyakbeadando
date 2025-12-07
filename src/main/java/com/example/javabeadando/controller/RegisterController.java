package com.example.javabeadando.controller;

import com.example.javabeadando.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "A jelszavak nem egyeznek!");
            return "register";
        }

        if (userService.emailExists(email)) {
            model.addAttribute("error", "Ez az email cím már használatban van!");
            return "register";
        }

        userService.registerUser(email, password);

        return "redirect:/login?registered";
    }
}

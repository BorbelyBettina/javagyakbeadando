// src/main/java/com/example/demo/controller/AdminController.java
package com.example.javabeadando.controller;

import com.example.javabeadando.entity.User;
import com.example.javabeadando.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"", "/", "/users"})
    public String showUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Admin – Felhasználók kezelése");
        return "admin/users";
    }
}
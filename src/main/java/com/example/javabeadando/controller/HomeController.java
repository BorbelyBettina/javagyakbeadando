package com.example.javabeadando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Visszaadja a "home.html"-t a templates könyvtárból
        return "fooldal/index";
    }
}

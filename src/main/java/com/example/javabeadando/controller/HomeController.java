package com.example.javabeadando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("companyName", "HorizonTours");
        model.addAttribute("slogan", "Fedezd fel a világ legszebb látnivalóit velünk!");
        return "fooldal/index"; // → templates/index.html
    }
}
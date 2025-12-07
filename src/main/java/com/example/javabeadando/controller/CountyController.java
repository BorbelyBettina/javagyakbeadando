package com.example.javabeadando.controller;

import com.example.javabeadando.repository.CountyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountyController {

    private final CountyRepository countyRepository;

    public CountyController(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @GetMapping("/counties")
    public String listCounties(Model model) {
        model.addAttribute("counties", countyRepository.findAll());
        return "counties/list";
    }
}

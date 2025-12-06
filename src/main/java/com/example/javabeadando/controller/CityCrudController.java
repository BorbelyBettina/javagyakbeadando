package com.example.javabeadando.controller;

import com.example.javabeadando.model.City;
import com.example.javabeadando.repository.CountyRepository;
import com.example.javabeadando.service.CityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crud")  // <-- itt változtattuk meg az útvonalat
public class CityCrudController {

    private final CityService cityService;
    private final CountyRepository countyRepository;

    public CityCrudController(CityService cityService, CountyRepository countyRepository) {
        this.cityService = cityService;
        this.countyRepository = countyRepository;
    }

    // --- Listázás (Read) ---
    @GetMapping
    public String listCities(Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "crud/list"; // a Thymeleaf template maradhat ugyanaz
    }

    // --- Új létrehozása (Create) ---
    @GetMapping("/new")
    public String newCityForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("counties", countyRepository.findAll()); // <-- add this
        return "crud/new";
    }

    @PostMapping
    public String createCity(@Valid @ModelAttribute City city,
                             BindingResult bindingResult,
                             @RequestParam Long megye,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("counties", countyRepository.findAll());
            return "crud/new";
        }

        // DO NOT set ID to null — user provides it
        city.setVersion(null); // version starts null

        city.setMegye(countyRepository.findById(megye)
                .orElseThrow(() -> new IllegalArgumentException("Invalid county ID: " + megye)));

        cityService.save(city);
        return "redirect:/crud";
    }



    // --- Szerkesztés (Update) ---
    @GetMapping("/edit/{id}")
    public String editCityForm(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        if (city == null) {
            throw new IllegalArgumentException("Invalid city Id: " + id);
        }
        model.addAttribute("city", city);
        model.addAttribute("counties", countyRepository.findAll());
        return "crud/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCity(@PathVariable Long id,
                             @ModelAttribute City cityForm,
                             @RequestParam Long megye) {

        City city = cityService.findById(id); // fetch the attached entity
        if (city == null) {
            throw new IllegalArgumentException("Invalid city Id: " + id);
        }

        // Update only the fields needed
        city.setNev(cityForm.getNev());
        city.setMegyeszekhely(cityForm.getMegyeszekhely());
        city.setMegyeijogu(cityForm.getMegyeijogu());
        city.setMegye(countyRepository.findById(megye)
                .orElseThrow(() -> new IllegalArgumentException("Invalid county ID: " + megye)));

        // Hibernate will automatically handle version for optimistic locking
        cityService.save(city);
        return "redirect:/crud";
    }



    // --- Törlés (Delete) ---
    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.deleteById(id);
        return "redirect:/crud";  // <-- redirect frissítve
    }
}

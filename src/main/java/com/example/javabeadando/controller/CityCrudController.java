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
@RequestMapping("/crud")
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
        return "crud/list";
    }

    // --- Új létrehozása (Create) ---
    @GetMapping("/new")
    public String newCityForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("counties", countyRepository.findAll());
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

        // ID automatikusan generálódik az adatbázisban
        city.setVersion(null);

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

        City city = cityService.findById(id);
        if (city == null) {
            throw new IllegalArgumentException("Invalid city Id: " + id);
        }

        // Frissítjük csak a szükséges mezőket
        city.setNev(cityForm.getNev());
        city.setMegyeszekhely(cityForm.getMegyeszekhely());
        city.setMegyeijogu(cityForm.getMegyeijogu());
        city.setMegye(countyRepository.findById(megye)
                .orElseThrow(() -> new IllegalArgumentException("Invalid county ID: " + megye)));

        cityService.save(city);
        return "redirect:/crud";
    }

    // --- Törlés (Delete) ---
    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.deleteById(id);
        return "redirect:/crud";
    }
}

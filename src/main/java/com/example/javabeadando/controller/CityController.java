package com.example.javabeadando.controller;

import com.example.javabeadando.repository.CityRepository;
import com.example.javabeadando.repository.CountyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CityController {

    private final CityRepository cityRepository;
    private final CountyRepository countyRepository;

    public CityController(CityRepository cityRepository, CountyRepository countyRepository) {
        this.cityRepository = cityRepository;
        this.countyRepository = countyRepository;
    }

    @GetMapping("/cities")
    public String listCities(
            @RequestParam(required = false) Integer countyId,
            Model model
    ) {
        if (countyId != null) {
            model.addAttribute("cities", cityRepository.findAllByMegye_Id(countyId));
        } else {
            model.addAttribute("cities", cityRepository.findAll());
        }

        model.addAttribute("counties", countyRepository.findAll());
        model.addAttribute("selectedCounty", countyId);

        return "cities/list";
    }
}

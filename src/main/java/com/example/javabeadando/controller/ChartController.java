package com.example.javabeadando.controller;

import com.example.javabeadando.model.County;
import com.example.javabeadando.repository.CityRepository;
import com.example.javabeadando.repository.CountyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChartController {

    private final CountyRepository countyRepository;
    private final CityRepository cityRepository;

    public ChartController(CountyRepository countyRepository, CityRepository cityRepository) {
        this.countyRepository = countyRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/chart")
    public String chart(Model model) {

        List<County> counties = countyRepository.findAll();

        List<CountyData> countyDataList = counties.stream()
                .map(c -> new CountyData(c.getNev(), cityRepository.findAllByMegye_Id(c.getId().intValue()).size()))
                .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount())) // csökkenő sorrend
                .toList();

        List<String> labels = countyDataList.stream()
                .map(CountyData::getName)
                .toList();

        List<Integer> data = countyDataList.stream()
                .map(CountyData::getCount)
                .toList();

        model.addAttribute("labels", labels);
        model.addAttribute("data", data);

        return "diagram/cities";
    }

    // Segédosztály a párosításra
    public static class CountyData {
        private final String name;
        private final int count;

        public CountyData(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }
    }

}

package com.example.javabeadando.service;
import java.util.Map;
import com.example.javabeadando.model.City;
import com.example.javabeadando.model.County;
import com.example.javabeadando.repository.CityRepository;

import java.util.List;

public interface CityService {
    List<City> findAll();
    City findById(Long id);
    City save(City city);
    void deleteById(Long id);
    boolean existsById(Long id);
    Map<Long, Long> getCountByCounty();
}

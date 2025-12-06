package com.example.javabeadando.service;

import com.example.javabeadando.model.City;
import com.example.javabeadando.model.County;
import com.example.javabeadando.repository.CityRepository;
import com.example.javabeadando.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cityRepository.existsById(id);
    }
    @Override
    public Map<Long, Long> getCountByCounty() {
        return cityRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        city -> city.getId(),
                        Collectors.counting()
                ));
    }




}

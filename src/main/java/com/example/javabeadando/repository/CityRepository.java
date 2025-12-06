package com.example.javabeadando.repository;

import com.example.javabeadando.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByMegye_Id(int megyeId);; // kisbetű 'megyeid', típus int
}


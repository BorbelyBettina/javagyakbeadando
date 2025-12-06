package com.example.javabeadando.repository;

import com.example.javabeadando.model.CityStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityStatisticRepository extends JpaRepository<CityStatistic, Long> {
}

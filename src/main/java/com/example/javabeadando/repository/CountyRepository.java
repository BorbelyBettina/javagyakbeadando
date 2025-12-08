package com.example.javabeadando.repository;

import com.example.javabeadando.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Long> {
    Long id(Long id);
}

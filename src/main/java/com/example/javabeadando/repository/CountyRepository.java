package com.example.javabeadando.repository;

import com.example.javabeadando.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
}

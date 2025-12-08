package com.example.javabeadando.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "megye")
public class Megye {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nev;

    private Timestamp created_at;
    private Timestamp updated_at;

    // getterek és setterek
}

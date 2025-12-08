package com.example.javabeadando.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "varos")
public class Varos {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(name = "megyeid", nullable = false)
    private Long megyeid;

    @Column(name = "megyeszekhely", nullable = false)
    private byte megyeszekhely;

    @Column(name = "megyeijogu", nullable = false)
    private byte megyeijogu;

    private Timestamp created_at;
    private Timestamp updated_at;

    // getterek és setterek
    // (Lombok @Data használható is)
}

package com.example.javabeadando.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lelekszam")
public class Lelekszam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "varosid", nullable = false)
    private Long varosid;

    @Column(nullable = false)
    private int ev;

    @Column(nullable = false)
    private int no;

    @Column(nullable = false)
    private int osszesen;

    private Timestamp created_at;
    private Timestamp updated_at;

    // getterek és setterek
}

package com.example.javabeadando.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzenetek")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nev;

    private String email;

    private String varos;

    private String kor;

    private String uzenet;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    // Konstruktor
    public Message() {
        this.createdAt = LocalDateTime.now();
    }

    // Getterek és setterek
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVaros() { return varos; }
    public void setVaros(String varos) { this.varos = varos; }

    public String getKor() { return kor; }
    public void setKor(String kor) { this.kor = kor; }

    public String getUzenet() { return uzenet; }
    public void setUzenet(String uzenet) { this.uzenet = uzenet; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}

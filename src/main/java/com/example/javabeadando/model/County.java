package com.example.javabeadando.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "megye")
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false)
    private String nev;

    // --- Kapcsolat: 1 megye → N város ---
    @OneToMany(mappedBy = "megye")
    private List<City> varosok;

    public County() {}

    public County(Long id, String nev) {
        this.id = id;
        this.nev = nev;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public List<City> getVarosok() { return varosok; }
    public void setVarosok(List<City> varosok) { this.varosok = varosok; }
}

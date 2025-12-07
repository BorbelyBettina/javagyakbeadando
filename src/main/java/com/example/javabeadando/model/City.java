package com.example.javabeadando.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "varos")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Név kötelező")
    private String nev;

    // --- Megye kapcsolat: Many-to-One ---
    @ManyToOne
    @JoinColumn(name = "megyeid", nullable = false )
    private County megye;      // helyettesíti a sima int megyeid mezőt

    @Version
    private Long version;

    // --- Megyeszekhely és megyeijogu mezők ---
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean megyeszekhely;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean megyeijogu;

    // --- Lélekszám kapcsolat: One-to-Many ---
    @OneToMany(mappedBy = "city")
    private List<CityStatistic> lelekszamok;

    // ---- Konstruktorok ----
    public City() {}

    public City(Long id, String nev, County megye, boolean megyeszekhely, boolean megyeijogu) {
        this.id = id;
        this.nev = nev;
        this.megye = megye;
        this.megyeszekhely = megyeszekhely;
        this.megyeijogu = megyeijogu;
    }

    // ---- Getterek / Setterek ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public County getMegye() { return megye; }
    public void setMegye(County megye) { this.megye = megye; }

    public Boolean getMegyeszekhely() { return megyeszekhely; }
    public void setMegyeszekhely(Boolean megyeszekhely) { this.megyeszekhely = megyeszekhely; }

    public Boolean getMegyeijogu() { return megyeijogu; }
    public void setMegyeijogu(Boolean megyeijogu) { this.megyeijogu = megyeijogu; }

    public List<CityStatistic> getLelekszamok() { return lelekszamok; }
    public void setLelekszamok(List<CityStatistic> lelekszamok) { this.lelekszamok = lelekszamok; }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

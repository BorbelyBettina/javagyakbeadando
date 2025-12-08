package com.example.javabeadando.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lelekszam")
public class CityStatistic {

    @EmbeddedId
    private CityStatisticId id;

    @ManyToOne
    @MapsId("varosid")  // összekapcsolja az ID mezőt a City-vel
    @JoinColumn(name = "varosid", nullable = false,  insertable=false, updatable=false)
    private City city;

    @Column(nullable = false)
    private int no;

    @Column(nullable = false)
    private int osszesen;


    public CityStatistic() {}

    public CityStatistic(CityStatisticId id, City city, int no, int osszesen) {
        this.id = id;
        this.city = city;
        this.no = no;
        this.osszesen = osszesen;
    }

    public CityStatisticId getId() { return id; }
    public void setId(CityStatisticId id) { this.id = id; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public int getEv() { return id.getEv(); }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getOsszesen() { return osszesen; }
    public void setOsszesen(int osszesen) { this.osszesen = osszesen; }
}

package com.example.javabeadando.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CityStatisticId implements Serializable {

    @Column(name = "varosid", columnDefinition = "BIGINT")
    private Long varosid;
    private int ev;

    public CityStatisticId() {}

    public CityStatisticId(Long varosid, int ev) {
        this.varosid = varosid;
        this.ev = ev;
    }

    public Long getVarosid() { return varosid; }
    public int getEv() { return ev; }

    public void setVarosid(Long varosid) { this.varosid = varosid; }
    public void setEv(int ev) { this.ev = ev; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityStatisticId)) return false;
        CityStatisticId that = (CityStatisticId) o;
        return ev == that.ev && Objects.equals(varosid, that.varosid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(varosid, ev);
    }
}

package com.u238.monitor.DBObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.u238.monitor.model.Country;

import javax.persistence.*;


@Entity
@Table(name = "country_data")
public class CountryDBObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private long active;

    @Column(name = "deaths")
    private long deaths;

    @Column(name = "recovered")
    private long recovered;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "summary_id")
    private SummaryDBObject summaryId;


    public CountryDBObject() {
    }

    public CountryDBObject(Country country) {
        this.code = country.getIso3();
        this.name = country.getCountry();
        this.active = country.getActive();
        this.deaths = country.getDeaths();
        this.recovered = country.getRecovered();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public SummaryDBObject getSummaryId() {
        return summaryId;
    }

    void setSummaryId(SummaryDBObject summaryId) {
        this.summaryId = summaryId;
    }


    @Override
    public String toString() {
        return "CountryDBObject{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", summaryId=" + summaryId +
                '}';
    }
}

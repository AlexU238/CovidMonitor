package com.u238.monitor.DBObjects;

import com.u238.monitor.model.Summary;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "day_summary")
public class SummaryDBObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "cases")
    private long cases;

    @Column(name = "deaths")
    private long deaths;

    @Column(name = "recovered")
    private long recovered;

    @Column(name = "active")
    private long active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "summaryId")
    private List<CountryDBObject> countries;


    public SummaryDBObject() {
    }

    public SummaryDBObject(Date date, Summary summary) {
        this.date = date;
        this.cases = summary.getCases();
        this.deaths = summary.getDeaths();
        this.recovered = summary.getRecovered();
        this.active = summary.getActive();
    }

    public void addCountry(CountryDBObject countryDBObject) {
        if (countries == null) {
            countries = new ArrayList<CountryDBObject>();
        }
        countryDBObject.setSummaryId(this);
        countries.add(countryDBObject);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
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

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public List<CountryDBObject> getCountries() {
        return countries;
    }

}

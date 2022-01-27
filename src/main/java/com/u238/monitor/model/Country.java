package com.u238.monitor.model;

public class Country {
    private String country;
    private CountryInfo countryInfo;
    private String iso3;
    private long active;
    private long deaths;
    private long recovered;

    public Country() {
    }

    public Country(String country, String iso3, long active, long deaths, long recovered) {
        this.country = country;
        this.iso3 = iso3;
        this.active = active;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3() {
        this.iso3 = this.getCountryInfo().getIso3();
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

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", active=" + active +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}

package com.u238.monitor.dao;

import com.u238.monitor.DBObjects.CountryDBObject;

public interface CountryDAOToday {

    CountryDBObject getCountryByName(String countryName);

}

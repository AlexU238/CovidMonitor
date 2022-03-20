package com.u238.monitor.service;

import com.u238.monitor.model.Country;

import java.util.List;

interface CountryDataToFile {

    void createFile(List<Country> countryList);
}

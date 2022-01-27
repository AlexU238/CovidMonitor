package com.u238.monitor.service;

import com.u238.monitor.DBObjects.CountryDBObject;
import com.u238.monitor.DBObjects.SummaryDBObject;
import org.springframework.core.io.InputStreamResource;


public interface DataPresentingService {

    CountryDBObject getCountryByName(String name);

    SummaryDBObject getTodaySummary();

    InputStreamResource getCSV();
}

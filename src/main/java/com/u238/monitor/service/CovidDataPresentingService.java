package com.u238.monitor.service;

import com.u238.monitor.DBObjects.CountryDBObject;
import com.u238.monitor.DBObjects.SummaryDBObject;
import com.u238.monitor.dao.CountryDAOToday;
import com.u238.monitor.dao.SummaryDAOToday;
import com.u238.monitor.utils.GetCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CovidDataPresentingService implements DataPresentingService {

    private CountryDAOToday countryDAOToday;
    private SummaryDAOToday summaryDAOToday;
    private GetCSV csv;

    @Autowired
    public CovidDataPresentingService(@Qualifier("countryDAO") CountryDAOToday countryDAOToday,
                                      @Qualifier("summaryDAO") SummaryDAOToday summaryDAOToday,
                                      @Qualifier("CSVLoader") GetCSV csv) {
        this.countryDAOToday = countryDAOToday;
        this.summaryDAOToday = summaryDAOToday;
        this.csv = csv;
    }

    @Override
    @Transactional
    public CountryDBObject getCountryByName(String name) {
        return countryDAOToday.getCountryByName(name);
    }

    @Override
    @Transactional
    public SummaryDBObject getTodaySummary() {
        return summaryDAOToday.getTodaySummary();
    }

    @Override
    public InputStreamResource getCSV() {
        return csv.getFile();
    }
}

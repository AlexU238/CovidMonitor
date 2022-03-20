package com.u238.monitor.service;

import com.u238.monitor.DBObjects.CountryDBObject;
import com.u238.monitor.DBObjects.SummaryDBObject;
import com.u238.monitor.model.Country;
import com.u238.monitor.model.Summary;
import com.u238.monitor.utils.StringDateFormatter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CovidDataCollectionService implements DataCollectionService {

    private RestTemplate restTemplate;
    private EntityManager entityManager;
    private String restUrlCountries;
    private String restUrlAll;

    private List<Country> countryList;

    @Autowired
    public CovidDataCollectionService(RestTemplate restTemplate,
                                      EntityManager entityManager,
                                      @Value("${rest.url.countries}") String restUrlCountries,
                                      @Value("${rest.url.all}") String restUrlAll) {
        this.restTemplate = restTemplate;
        this.entityManager = entityManager;
        this.restUrlCountries = restUrlCountries;
        this.restUrlAll = restUrlAll;
    }


    //the method is scheduled to run every day
    //WARNING: not tested, do not know if works, because never ran the app for 24 hours
    @Scheduled(cron = "@daily")
    @Override
    public void collectData() {
        getCountryData();
        fileCountryData(new FormatNameAndActiveToCSV());
        sendToDB();
    }


    private Summary collectDataSummary() {
        return restTemplate.getForObject(restUrlAll, Summary.class);
    }

    private void getCountryData() {
        ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(restUrlCountries,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Country>>() {
                });

        countryList = responseEntity.getBody();
        if (countryList != null) {
            for (Country c : countryList) {
                c.setIso3();
            }
        }

    }

    private void fileCountryData(CountryDataToFile countryDataToFile) {
        countryDataToFile.createFile(countryList);
    }

    @Override
    public void sendToDB() {

        Summary summary = collectDataSummary();
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Date date = StringDateFormatter.formatStringToDate(formattedDate);
        SummaryDBObject sDbObject = new SummaryDBObject(date, summary);

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(sDbObject);

        for (Country country : countryList) {
            if (country.getIso3() != null) {
                CountryDBObject countryDBObject = new CountryDBObject(country);
                sDbObject.addCountry(countryDBObject);
                session.saveOrUpdate(countryDBObject);
            }
        }
    }


}


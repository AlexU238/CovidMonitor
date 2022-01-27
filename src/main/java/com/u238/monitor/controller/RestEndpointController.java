package com.u238.monitor.controller;


import com.u238.monitor.DBObjects.CountryDBObject;
import com.u238.monitor.DBObjects.SummaryDBObject;
import com.u238.monitor.service.DataPresentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestEndpointController {

    private DataPresentingService dataPresentingService;


    @Autowired
    public RestEndpointController(@Qualifier("covidDataPresentingService") DataPresentingService dataPresentingService) {
        this.dataPresentingService = dataPresentingService;
    }

    @GetMapping("/downloadCSV")
    public ResponseEntity<Resource> getCSVFile() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= Covid_data.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(dataPresentingService.getCSV());
    }


    @GetMapping("/search/{country}")
    public CountryDBObject getCountry(@PathVariable String country) {
        return dataPresentingService.getCountryByName(country);
    }

    @GetMapping("/summary")
    public SummaryDBObject getSummary() {
        return dataPresentingService.getTodaySummary();
    }

}

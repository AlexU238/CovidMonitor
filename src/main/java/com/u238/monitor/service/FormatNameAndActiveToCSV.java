package com.u238.monitor.service;

import com.u238.monitor.model.Country;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

class FormatNameAndActiveToCSV implements CountryDataToFile {


    @Override
    public void createFile(List<Country> countryList) {
        //was not able to have a method to create a folder for covidData.csv
        //program just ignored all my tries
        //find a method if possible
        try (PrintWriter writer = new PrintWriter("covidData.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("location");
            sb.append(',');
            sb.append("cases");
            sb.append('\n');

            for (Country country : countryList) {
                sb.append(country.getCountry());
                sb.append(',');
                sb.append(country.getActive());
                sb.append('\n');
            }

            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

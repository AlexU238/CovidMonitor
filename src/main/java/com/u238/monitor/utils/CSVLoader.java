package com.u238.monitor.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;

@Component
public class CSVLoader implements GetCSV {


    @Override
    public InputStreamResource getFile() {
        String filename = "covidData.csv";
        InputStreamResource file = null;
        try {
            file = new InputStreamResource(retrieveByteArrayInputStream(new File(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private ByteArrayInputStream retrieveByteArrayInputStream(File file) throws IOException {

        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

    }
}

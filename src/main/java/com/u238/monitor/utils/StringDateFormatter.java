package com.u238.monitor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface StringDateFormatter {

    static Date formatStringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date theDate = null;
        try {
            theDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return theDate;
    }
}

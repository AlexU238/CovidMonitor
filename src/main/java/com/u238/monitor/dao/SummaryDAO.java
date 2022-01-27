package com.u238.monitor.dao;

import com.u238.monitor.DBObjects.SummaryDBObject;
import com.u238.monitor.model.Summary;
import com.u238.monitor.utils.StringDateFormatter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class SummaryDAO implements SummaryDAOToday {

    private EntityManager entityManager;

    @Autowired
    public SummaryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public SummaryDBObject getTodaySummary() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("select s from SummaryDBObject as s WHERE s.date = current_date ", SummaryDBObject.class);

        if (theQuery != null) {
            return (SummaryDBObject) theQuery.getSingleResult();
        } else {
            Date date = StringDateFormatter.formatStringToDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return new SummaryDBObject(date, new Summary(0, 0, 0, 0));
        }

    }
}

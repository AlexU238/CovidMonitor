package com.u238.monitor.dao;

import com.u238.monitor.DBObjects.CountryDBObject;
import com.u238.monitor.model.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CountryDAO implements CountryDAOToday {

    private EntityManager entityManager;

    @Autowired
    public CountryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CountryDBObject getCountryByName(String countryName) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery;

        if (countryName != null && countryName.trim().length() > 0) {

            theQuery = currentSession.createQuery("select c from CountryDBObject as c, SummaryDBObject as s WHERE (lower(c.name) = :name AND c.summaryId=s AND s.date = current_date )", CountryDBObject.class);

            theQuery.setParameter("name", countryName.toLowerCase());
            System.out.println(theQuery.getSingleResult());
            return (CountryDBObject) theQuery.getSingleResult();
        } else {
            return new CountryDBObject(new Country("no data", "no", 0, 0, 0));
        }

    }
}

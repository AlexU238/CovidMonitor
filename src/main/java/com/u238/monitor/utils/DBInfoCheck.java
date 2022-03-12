package com.u238.monitor.utils;


import com.u238.monitor.DBObjects.SummaryDBObject;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Component
public class DBInfoCheck implements DBCheckUtil{

    private EntityManager entityManager;

    @Autowired
    public DBInfoCheck(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //to prevent NoResultException when starting the application with empty DB
    public boolean check(){
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("select s from SummaryDBObject as s WHERE s.date = current_date ", SummaryDBObject.class);

        SummaryDBObject tmp;

        try {
            tmp = (SummaryDBObject) theQuery.getSingleResult();
        }catch (NoResultException r){
            r.getMessage();
            return false;
        }

        return true;
    }
}

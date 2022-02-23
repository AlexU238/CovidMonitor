package com.u238.monitor.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {

    @Pointcut("execution(* com.u238.monitor.controller.*.*(..))")
    void forControllerPackage(){

    }

    @Pointcut("execution(* com.u238.monitor.service.*.*(..))")
    void forServicePackage(){

    }

    //not used but may come in handy in the future
    @Pointcut("execution(* com.u238.monitor.dao.*.*(..))")
    void forDAOPackage(){

    }

    @Pointcut("execution(* com.u238.monitor.dao.CountryDAO.*(..))")
    void forCountryDAOPackage(){

    }

    @Pointcut("execution(* com.u238.monitor.utils.CSVLoader.getFile(..))")
    void forCSVLoader(){

    }
}

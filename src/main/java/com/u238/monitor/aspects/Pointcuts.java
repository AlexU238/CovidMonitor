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


    @Pointcut("execution(* com.u238.monitor.utils.*.*(..))")
    void forUtilPackage(){

    }

    @Pointcut("execution(* com.u238.monitor.dao.CountryDAO.*(..))")
    void forCountryDAOPackage(){

    }

    @Pointcut("execution(* com.u238.monitor.utils.CSVLoader.getFile(..))")
    void forCSVLoader(){

    }
}

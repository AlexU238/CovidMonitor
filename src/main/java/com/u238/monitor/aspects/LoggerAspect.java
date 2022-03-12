package com.u238.monitor.aspects;

import com.u238.monitor.DBObjects.CountryDBObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @After("com.u238.monitor.aspects.Pointcuts.forControllerPackage()")
    public void controllerLog(JoinPoint joinPoint) {
        logger.info("Controller Method: "+joinPoint.getSignature());
    }

    @After("com.u238.monitor.aspects.Pointcuts.forServicePackage()")
    public void serviceLog(JoinPoint joinPoint) {
        logger.info("Service Method: "+joinPoint.getSignature());
    }

    @After("com.u238.monitor.aspects.Pointcuts.forCSVLoader()")
    public void CSVLoaderLog() {
        logger.info("CSV requested");
    }

    @AfterReturning(pointcut="com.u238.monitor.aspects.Pointcuts.forCountryDAOPackage()",
            returning="result")
    public void daoCountryLog(CountryDBObject result) {
        logger.info("Getting object form DB: "+result.toString());
    }

    @After("com.u238.monitor.aspects.Pointcuts.forUtilPackage()")
    public void utilLog(JoinPoint joinPoint) {
        logger.info("Util Method: "+joinPoint.getSignature());
    }

}

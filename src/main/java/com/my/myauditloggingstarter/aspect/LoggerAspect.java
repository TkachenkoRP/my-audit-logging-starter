package com.my.myauditloggingstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Aspect
public class LoggerAspect {
    private final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);
    private final Marker marker = MarkerFactory.getMarker("LOGGER");

    @Pointcut("execution(public * *..*Controller.*(..))")
    public void annotatedByEnableLoggingAspect() {
    }

    @Around("annotatedByEnableLoggingAspect()")
    public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOG.info(marker, "Calling method {}", proceedingJoinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis() - start;
        LOG.info(marker, "Execution of method {} finished. Execution time is {} ms.", proceedingJoinPoint.getSignature(), end);
        return result;
    }
}

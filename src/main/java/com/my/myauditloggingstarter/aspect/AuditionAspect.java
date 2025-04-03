package com.my.myauditloggingstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Arrays;

@Aspect
public class AuditionAspect {
    private final Logger LOG = LoggerFactory.getLogger(AuditionAspect.class);
    private final Marker marker = MarkerFactory.getMarker("AUDITION");

    @Pointcut("execution(public * *..*Controller.*(..))")
    public void audition() {
    }

    @Around("execution(public * *..*Controller.*(..))")
    public Object logControllerMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        String methodName = joinPoint.getSignature().toShortString();
        stringBuilder.append("Entering controller method: ").append(methodName);
        Object[] methodArgs = joinPoint.getArgs();
        stringBuilder.append("Method parameters: ").append(Arrays.toString(methodArgs));
        LOG.info(marker, stringBuilder.toString());
        stringBuilder.setLength(0);
        try {
            Object result = joinPoint.proceed();
            stringBuilder.append("Exiting controller method: ").append(methodName);
            stringBuilder.append("Method execution result: ").append(result);
            LOG.info(marker, stringBuilder.toString());
            return result;
        } catch (Exception e) {
            LOG.error(marker, "Exception in controller method: {}", methodName, e);
            throw e;
        }
    }
}

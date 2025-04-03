package com.my.myauditloggingstarter.config;

import com.my.myauditloggingstarter.aspect.AuditionAspect;
import com.my.myauditloggingstarter.aspect.LoggerAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class AspectConfig {
    @Bean
    public LoggerAspect loggerAspect() {
        return new LoggerAspect();
    }

    @Bean
    public AuditionAspect auditionAspect() {
        return new AuditionAspect();
    }
}

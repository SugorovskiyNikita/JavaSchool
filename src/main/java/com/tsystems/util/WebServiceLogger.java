package com.tsystems.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

/**
 * Created by nikita on 10.11.2020.
 */
@Aspect
@Component
public class WebServiceLogger {

    private final Logger logger = Logger.getLogger(WebServiceLogger.class);

    @After("execution(* com.tsystems.business.services.implementations.MessageService.sendMessage(..))")
    public void webServiceLogger(JoinPoint joinPoint) {
        String message = joinPoint.getSignature().getName();
        logger.info("Send message to ActiveMq." + message);
    }
}

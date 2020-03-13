package com.safonov.demo.application.common.aspects.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Pointcut("within(com.safonov.demo.application.web.controller.*)")
    public void controllers(){}

    @Around("controllers() && @annotation(loggable)")
    public Object fullAudit(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        Object result;
        log.info("\nEntered method {} \nwith args {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        result = joinPoint.proceed(joinPoint.getArgs());
        stopWatch.stop();
        if(result != null){
            log.info("\nOut of method with result {} \nduring {} ms.", result.toString(), stopWatch.getLastTaskTimeMillis());
        } else log.info("\nOut of method with result null \nduring {} ms.", stopWatch.getLastTaskTimeMillis());
        return result;
    }

    @Around("@annotation(loggable)")
    public Object minAudit(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        stopWatch.stop();
        log.error("\n" + joinPoint.getSignature().toShortString() +  " during {} ms.", stopWatch.getLastTaskTimeMillis());
        return result;
    }
}

package com.springapp.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect{
    @Before("execution(* com.springapp.demo..*(..))")
    public void logBefore(){
        System.out.println("Logging method is about to execute");
    }

    @Around("execution(* com.springapp.demo..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(
            joinPoint.getSignature().getName()
            + " took "
            + (end - start)
            + " ms"
        );
        return result;
    }
}
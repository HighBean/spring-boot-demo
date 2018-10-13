package com.boomboo.demo.aspect;

import com.boomboo.demo.annotation.UrlStatistics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class UrlStaisticsAspect {

    @Around(value = "@annotation(UrlStatistics)")
    public Object alertMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        try {
            return joinPoint.proceed(args);
        } catch (Throwable ex) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            UrlStatistics urlStatistics = method.getAnnotation(UrlStatistics.class);
            throw ex;
        }
    }


    @Around(value = "@within(UrlStatistics)")
    public Object alertType(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        try {
            return joinPoint.proceed(args);
        } catch (Throwable ex) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            UrlStatistics urlStatistics = method.getAnnotation(UrlStatistics.class);
            throw ex;
        }
    }


}

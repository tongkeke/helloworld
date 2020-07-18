package com.it.springboot.aop;

import com.it.springboot.util.Constants;
import com.it.springboot.util.ExceptionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Copyright (C), 2018-2020, xxx有限公司
 *
 * @FileName: LoggingAspect
 * @Author: dawsontong
 * @Date: 2020/7/14 14:11
 * @Description:
 */
@Component
@Aspect
public class LoggingAspect {
    private final static Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    @Inject
    Environment environment;

    @Pointcut("within(com.it.springboot.serviceImpl..*)")
    public void loggingPointCut(){}

    //记录运行时异常
    @AfterThrowing(pointcut = "loggingPointCut()",throwing= "e")
    public void loggingAfter(JoinPoint joinPoint,Throwable e){
        if(environment.getActiveProfiles().equals(Constants.SPRING_Profile_Development)){
            log.error("dev : Exception in {}.{}() with cause = \'{}\' and exception = \'{}\' and stackTraceInfo = \'{}\'",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),e.getCause() != null ? e.getCause() : "Null",e.getMessage(),ExceptionUtil.getStackTrace(e));
        }else{
            log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\' and stackTraceInfo = \'{}\'",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),e.getCause() != null ? e.getCause() : "Null",e.getMessage(),ExceptionUtil.getStackTrace(e));
        }

    }



    @Around("loggingPointCut()")
    public Object loggindAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if(log.isDebugEnabled()){
            log.debug("Enter: {}.{}() with argument[s] = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }else{
            log.info("Enter: {}.{}() with argument[s] = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if(log.isDebugEnabled()){
                log.debug("Exit: {}.{}() with argument[s] = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            }else{
                log.info("Exit: {}.{}() with argument[s] = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            }
            return result;
        } catch ( IllegalArgumentException e ) {
            log.error("IllegalArgument: {}.{}() with argument[s] = {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            throw e;
        }
    }



}

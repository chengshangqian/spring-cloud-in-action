package com.fandou.coffee.learning.springcloud.common.support;

import com.fandou.coffee.learning.springcloud.common.model.Log;
import com.fandou.coffee.learning.springcloud.common.utils.HttpUtils;
import com.fandou.coffee.learning.springcloud.common.utils.UserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
public class DoLogAspect {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(DoLogAspect.class);

    private final MessageSender messageSender; // defaultMessageSender

    public DoLogAspect(MessageSender messageSender){
        this.messageSender = messageSender;
    }

    @Pointcut("@annotation(com.fandou.coffee.learning.springcloud.common.support.DoLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void save(JoinPoint point) throws JsonProcessingException {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        Log log = new Log();

        DoLog doLog = method.getAnnotation(DoLog.class);
        if (null != doLog) {
            log.setOperation(doLog.value());
        }

        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        log.setMethod(String.format("%s.%s()", className, methodName));

        Object[] args = point.getArgs();
        if(null != args && args.length > 0) {
            String params = new ObjectMapper().writeValueAsString(args);
            if (null != params && params.length() > 0) {
                log.setParams(params);
            }
        }

        log.setIp(HttpUtils.getIpAddress());

        log.setUsername(UserUtils.getCurrentPrinciple());

        log.setCreateDate(new Date());

        messageSender.sendMessage(log);
    }
}

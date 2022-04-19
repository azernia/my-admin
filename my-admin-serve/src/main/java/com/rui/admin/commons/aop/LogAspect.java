package com.rui.admin.commons.aop;

import cn.hutool.core.util.ObjectUtil;
import com.rui.admin.commons.annotation.log.SystemLog;
import com.rui.admin.commons.exception.BusinessException;
import com.rui.admin.system.model.entity.Log;
import com.rui.admin.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rui
 * @since 2022/4/19 14:20
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.rui.admin.commons.annotation.log.SystemLog)")
    public void point() {

    }

    @Around("point()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目标方法的返回值
        Object proceed;
        try {
            // handleBefore(joinPoint);
            proceed = joinPoint.proceed();
            // handleAfter(proceed);
            SystemLog systemLog = getSystemLog(joinPoint);
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (ObjectUtil.isNull(requestAttributes)) {
                throw new BusinessException("请求参数为空");
            }
            HttpServletRequest request = requestAttributes.getRequest();
            Log log = new Log();
            log.setRequestUrl(String.valueOf(request.getRequestURL()))
                    .setBusinessName(systemLog.businessName())
                    .setHttpMethod(request.getMethod())
                    .setClassName(joinPoint.getSignature().getDeclaringTypeName())
                    .setMethod(joinPoint.getSignature().getName())
                    .setIp(request.getRemoteHost());
            logService.save(log);
        } finally {
            log.info("====end====" + System.lineSeparator());
        }
        return proceed;
    }

    // private void handleAfter(Object proceed) {
    //     log.info("Response:{}", JSON.toJSONString(proceed));
    // }

    // private void handleBefore(ProceedingJoinPoint joinPoint) {
    //     ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    //     if (ObjectUtil.isNull(requestAttributes)) {
    //         throw new BusinessException("请求参数为空");
    //     }
    //     HttpServletRequest request = requestAttributes.getRequest();
    //     // 获取被增强方法上的注解对象
    //     SystemLog systemLog = getSystemLog(joinPoint);
    //     log.info("=====start=====");
    //     log.info("URL:{}", request.getRequestURL());
    //     log.info("BusinessName:{}", systemLog.businessName());
    //     log.info("HTTP Method:{}", request.getMethod());
    //     log.info("Class Method:{}, {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    //     log.info("IP:{}", request.getRemoteHost());
    //     log.info("Request Args:{}", JSON.toJSONString(joinPoint.getArgs()));
    // }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class);
    }

}

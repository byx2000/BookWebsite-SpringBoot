package com.byx.aop;

import com.byx.domain.ResultInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 在Controller层统一处理异常
 */
@Component
@Aspect
public class ExceptionHandler
{
    @Pointcut("execution(com.byx.domain.ResultInfo com.byx.controller.*.*(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public ResultInfo around(ProceedingJoinPoint pjp)
    {
        try
        {
            return (ResultInfo) pjp.proceed();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return ResultInfo.fail("服务器内部错误");
        }
    }
}
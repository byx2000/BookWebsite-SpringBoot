package com.byx.aop;

import com.byx.domain.ResultInfo;
import com.byx.exception.BookWebsiteException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 异常处理切面
 */
@Component
@Aspect
public class ExceptionAspect
{
    @Pointcut("execution(com.byx.domain.ResultInfo com.byx.controller.*.*(..))")
    public void pointcut() {}

    /**
     * 捕获controller层的异常
     * @param pjp 连接点
     * @return {false, msg}
     */
    @Around("pointcut()")
    public ResultInfo catchControllerException(ProceedingJoinPoint pjp)
    {
        try
        {
            return (ResultInfo) pjp.proceed();
        }
        catch (BookWebsiteException e)
        {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return ResultInfo.fail("服务器内部错误");
        }
    }
}
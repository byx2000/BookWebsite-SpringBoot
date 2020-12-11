package com.byx.aop;

import com.byx.domain.ResultInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 登录校验切面
 */
@Component
@Aspect
@Order(2)
public class LoginVerifyAspect
{
    @Autowired
    private HttpSession session;

    @Pointcut("execution(com.byx.domain.ResultInfo com.byx.controller.*.*(..))" +
            "&& @annotation(com.byx.annotation.RequireLogin)")
    public void pointcut() {}

    /**
     * 登录校验
     * @param pjp 连接点
     * @return 错误消息或结果
     */
    @Around("pointcut()")
    public ResultInfo handle(ProceedingJoinPoint pjp) throws Throwable
    {
        if (session.getAttribute("user") == null) return ResultInfo.fail("当前未登录");
        return (ResultInfo) pjp.proceed();
    }
}

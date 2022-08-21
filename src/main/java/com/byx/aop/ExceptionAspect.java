package com.byx.aop;

import com.byx.domain.ResultInfo;
import com.byx.exception.LogicException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;

/**
 * 统一异常处理切面
 */
@Component
@Aspect
@Order(1)
public class ExceptionAspect {
    @Pointcut("execution(com.byx.domain.ResultInfo com.byx.controller.*.*(..))")
    public void pointcut() {
    }

    /**
     * 捕获controller层的异常
     *
     * @param pjp 连接点
     * @return 错误消息或结果
     */
    @Around("pointcut()")
    public ResultInfo handle(ProceedingJoinPoint pjp) {
        try {
            return (ResultInfo) pjp.proceed();
        }
        // 参数异常
        catch (ConstraintViolationException e) {
            e.printStackTrace();
            return ResultInfo.fail("参数错误：" + e.getMessage());
        }
        // 自定义异常
        catch (LogicException e) {
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
        // 未知异常
        catch (Throwable e) {
            e.printStackTrace();
            return ResultInfo.fail("服务器内部错误：" + e.getMessage());
        }
    }
}
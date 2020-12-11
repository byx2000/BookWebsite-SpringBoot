package com.byx.aop;

import com.byx.domain.ResultInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;

/**
 * 参数校验切面
 */
@Component
@Aspect
public class ParamsValidateAspect
{
    @Pointcut("execution(com.byx.domain.ResultInfo com.byx.controller.*.*(..))")
    public void pointcut() {}

    /**
     * 捕获参数校验异常
     * @param pjp 连接点
     * @return 统一错误消息
     */
    @Around("pointcut()")
    public ResultInfo catchParamsValidationException(ProceedingJoinPoint pjp)
    {
        try
        {
            return (ResultInfo) pjp.proceed();
        }
        catch (ConstraintViolationException e)
        {
            e.printStackTrace();
            return ResultInfo.fail("参数错误");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return ResultInfo.fail("服务器内部错误");
        }
    }
}

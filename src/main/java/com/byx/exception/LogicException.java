package com.byx.exception;

/**
 * 业务逻辑异常
 */
public class LogicException extends RuntimeException
{
    private final String message;

    public LogicException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}

package com.byx.exception;

public class BookWebsiteException extends RuntimeException
{
    private final String message;

    public BookWebsiteException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}

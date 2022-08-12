package com.swiggy.exceptions;

public class IncorrectMainArgumentException extends Exception
{
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return "\n Incorrect Option , Correct Options: 1/2 ";
    }
}

package com.customer_management.exception;

public class ExceptionTest extends RuntimeException{
    private String errorCode;
    private String message;

    public ExceptionTest(){
        super();
    }
}

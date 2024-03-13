package com.customer_management.exception;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AlreadyExistsException extends RuntimeException{
    private HttpStatus statusCode;
    private String message;

    public AlreadyExistsException(){
        super();
    }

    public AlreadyExistsException(String message){
        this.message=message;
    }

    public AlreadyExistsException(String message, HttpStatus errorCode){
        this.statusCode =errorCode;
        this.message=message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AlreadyExistsException{" +
                "errorCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}


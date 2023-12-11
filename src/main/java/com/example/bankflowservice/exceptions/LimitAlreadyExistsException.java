package com.example.bankflowservice.exceptions;

public class LimitAlreadyExistsException extends RuntimeException{
    public LimitAlreadyExistsException(String message){
        super(message);
    }
}

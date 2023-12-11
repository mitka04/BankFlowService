package com.example.bankflowservice.exceptions;

public class InvalidLimitDatetimeException extends RuntimeException{
    public InvalidLimitDatetimeException(String message){
        super(message);
    }
}

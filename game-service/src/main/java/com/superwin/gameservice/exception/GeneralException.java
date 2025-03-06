package com.superwin.gameservice.exception;

public class GeneralException extends RuntimeException{
    public GeneralException(String message, Exception e){
        super(message, e);
    }
}

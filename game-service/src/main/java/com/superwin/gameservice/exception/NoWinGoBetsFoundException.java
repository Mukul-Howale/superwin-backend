package com.superwin.gameservice.exception;

public class NoWinGoBetsFoundException extends RuntimeException{
    public NoWinGoBetsFoundException(String message){
        super(message);
    }
}

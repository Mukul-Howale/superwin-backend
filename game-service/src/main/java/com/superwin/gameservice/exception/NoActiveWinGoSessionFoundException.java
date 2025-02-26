package com.superwin.gameservice.exception;

public class NoActiveWinGoSessionFoundException extends RuntimeException{
    public NoActiveWinGoSessionFoundException(String message){
        super(message);
    }
}

package com.superwin.profileservice.exception;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException(String message){
        super(message);
    }
}

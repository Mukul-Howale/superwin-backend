package com.superwin.gameservice.exception;

import com.superwin.gameservice.exception.gameexception.GameNotFoundException;
import com.superwin.gameservice.exception.gameexception.GameUnderMaintenanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    /**
     * Point to note :
     * For custom exceptions e.g. ProfileNotFoundException.class,
     * extend RuntimeException, not Throwable
     * If used Throwable, the custom exception needs
     * to be caught in catch block where ever used
     */

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<String> handleGeneralException(GeneralException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoActiveWinGoSessionFoundException.class)
    public ResponseEntity<String> handleSessionNotFoundException(NoActiveWinGoSessionFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleGameNotFoundException(GameNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GameUnderMaintenanceException.class)
    public ResponseEntity<String> handleGameUnderMaintenanceException(GameUnderMaintenanceException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoWinGoSessionFoundException.class)
    public ResponseEntity<String> handleNoWinGoSessionFoundException(NoWinGoSessionFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBetException.class)
    public ResponseEntity<String> handleIllegalBetException(InvalidBetException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreateSessionException.class)
    public ResponseEntity<String> handleCreateSessionException(CreateSessionException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LastRunException.class)
    public ResponseEntity<String> handleLastRunException(LastRunException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoWinGoBetsFoundException.class)
    public ResponseEntity<String> handleNoWinGoBetsFoundException(NoWinGoBetsFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

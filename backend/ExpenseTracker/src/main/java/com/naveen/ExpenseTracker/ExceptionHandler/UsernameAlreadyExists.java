package com.naveen.ExpenseTracker.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String message){
        super(message);

    }}

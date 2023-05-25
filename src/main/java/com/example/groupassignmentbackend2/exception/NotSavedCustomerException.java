package com.example.groupassignmentbackend2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotSavedCustomerException extends Throwable {

    public NotSavedCustomerException(String message){
        super(message);
    }
}
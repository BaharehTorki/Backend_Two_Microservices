package com.example.groupassignmentbackend2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundCustomerException extends Throwable{

    public NotFoundCustomerException(String message){
        super(message);
    }



}
package com.example.pweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UnexpectedException extends RuntimeException{

    public UnexpectedException(String message) {
        super(message);
    }
}

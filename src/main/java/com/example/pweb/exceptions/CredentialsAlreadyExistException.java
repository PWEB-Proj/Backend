package com.example.pweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CredentialsAlreadyExistException extends RuntimeException{
    public CredentialsAlreadyExistException(String message) {
        super(message);
    }
}

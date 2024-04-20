package com.example.pweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CategoryTomTomException extends RuntimeException{
    public CategoryTomTomException(String message) {
        super(message);
    }
}

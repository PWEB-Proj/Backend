package com.example.pweb.controller;

import com.example.pweb.exceptions.CredentialsAlreadyExistException;
import com.example.pweb.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<?> handleBadCredentials(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({CredentialsAlreadyExistException.class})
    public ResponseEntity<?> handleCredentialsAlreadyExist(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleBadRequest(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

package com.example.jwtauthentication.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends Exception {

    private int code;
    private HttpStatus httpStatus;
    private Object responseObject;

    public UsernameNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public UsernameNotFoundException(String exceptionMessage, HttpStatus httpStatus) {
        super(exceptionMessage);
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
    }

    public UsernameNotFoundException(String exceptionMessage, Object responseObject, HttpStatus httpStatus) {
        super(exceptionMessage);
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.responseObject = responseObject;
    }



}

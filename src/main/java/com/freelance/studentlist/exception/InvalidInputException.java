package com.freelance.studentlist.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException {

    protected HttpStatus httpStatus;

    public InvalidInputException(HttpStatus httpStatus, String message) {

        super(message);

        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

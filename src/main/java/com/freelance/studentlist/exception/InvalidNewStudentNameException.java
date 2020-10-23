package com.freelance.studentlist.exception;

import org.springframework.http.HttpStatus;

public class InvalidNewStudentNameException extends InvalidInputException {

    public InvalidNewStudentNameException(HttpStatus httpStatus, String name) {
        super(httpStatus, "Could not create a student: student's name, " + name + ", should not be empty.");
    }
}

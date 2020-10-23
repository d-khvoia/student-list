package com.freelance.studentlist.exception;

import org.springframework.http.HttpStatus;

public class InvalidNewStudentSurnameException extends InvalidInputException {

    public InvalidNewStudentSurnameException(HttpStatus httpStatus, String surname) {
        super(httpStatus, "Could not create a student: student's surname, " + surname + ", should not be empty.");
    }
}

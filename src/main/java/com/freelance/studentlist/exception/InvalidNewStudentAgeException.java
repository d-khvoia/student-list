package com.freelance.studentlist.exception;

import org.springframework.http.HttpStatus;

public class InvalidNewStudentAgeException extends InvalidInputException {

    public InvalidNewStudentAgeException(HttpStatus httpStatus, Integer age) {
        super(httpStatus, "Could not create a student: student's age, " + age + ", should not be empty or less than 16.");
    }

}

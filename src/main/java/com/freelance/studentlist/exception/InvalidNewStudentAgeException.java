package com.freelance.studentlist.exception;

public class InvalidNewStudentAgeException extends RuntimeException {

    public InvalidNewStudentAgeException(Integer age) {

        super("Could not create a student: student's age, " + age + ", should not be empty or less than 16.");
    }
}

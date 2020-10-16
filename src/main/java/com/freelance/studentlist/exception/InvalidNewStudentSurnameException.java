package com.freelance.studentlist.exception;

public class InvalidNewStudentSurnameException extends RuntimeException {

    public InvalidNewStudentSurnameException(String surname) {

        super("Could not create a student: student's surname, " + surname + ", should not be empty.");
    }
}

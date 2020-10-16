package com.freelance.studentlist.exception;

public class InvalidNewStudentNameException extends RuntimeException {

    public InvalidNewStudentNameException(String name) {

        super("Could not create a student: student's name, " + name + ", should not be empty.");
    }
}

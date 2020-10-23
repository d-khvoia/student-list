package com.freelance.studentlist.controller;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.exception.InvalidNewStudentAgeException;
import com.freelance.studentlist.exception.InvalidNewStudentNameException;
import com.freelance.studentlist.exception.InvalidNewStudentSurnameException;
import com.freelance.studentlist.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    @ResponseBody
    public Flux<Student> list() {
        return studentService.findAll();
    }

    @GetMapping("/add")
    @ResponseBody
    public Mono<Long> add(@RequestParam String name, @RequestParam String surname, @RequestParam Integer age) {
        try {
            return studentService.add(name, surname, age).map(student -> student.getId());
        }
        catch (InvalidNewStudentAgeException invNewStudAgeEx) {
            return Mono.error(invNewStudAgeEx);
        }
        catch (InvalidNewStudentNameException invNewStudNameEx) {
            return Mono.error(invNewStudNameEx);
        }
        catch (InvalidNewStudentSurnameException invNewStudSurnameEx) {
            return Mono.error(invNewStudSurnameEx);
        }
    }

    @ResponseStatus(
      value = HttpStatus.BAD_REQUEST,
      reason = "Invalid student's age: age should not be empty or less than 16.")
    @ExceptionHandler(InvalidNewStudentAgeException.class)
    public void invalidNewStudentAgeHandler() {

    }

    @ResponseStatus(
      value = HttpStatus.BAD_REQUEST,
      reason = "Invalid student's name: the name should not be empty.")
    @ExceptionHandler(InvalidNewStudentNameException.class)
    public void invalidNewStudentNameHandler() {

    }

    @ResponseStatus(
      value = HttpStatus.BAD_REQUEST,
      reason = "Invalid student's surname: the surname should not be empty.")
    @ExceptionHandler(InvalidNewStudentSurnameException.class)
    public void invalidNewStudentSurnameHandler() {

    }
}

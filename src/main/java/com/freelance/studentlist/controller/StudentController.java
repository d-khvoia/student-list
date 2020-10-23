package com.freelance.studentlist.controller;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.exception.InvalidInputException;
import com.freelance.studentlist.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        catch (InvalidInputException invalidInputException) {
            return Mono.error(invalidInputException);
        }
    }

    @ResponseStatus(
      value = HttpStatus.UNPROCESSABLE_ENTITY,
      reason = "Invalid input data for new student.")
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity invalidInputHandler(InvalidInputException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}

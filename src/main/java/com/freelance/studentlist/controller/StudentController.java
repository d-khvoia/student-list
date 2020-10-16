package com.freelance.studentlist.controller;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return studentService.add(name, surname, age).map(student -> student.getId());
    }
}

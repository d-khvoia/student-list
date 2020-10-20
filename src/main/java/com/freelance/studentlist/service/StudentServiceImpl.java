package com.freelance.studentlist.service;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Flux<Student> findAll() {

        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> add(String name, String surname, Integer age) {

        return studentRepository.save(new Student(name, surname, age));
    }
}

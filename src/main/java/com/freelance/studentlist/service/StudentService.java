package com.freelance.studentlist.service;

import com.freelance.studentlist.domain.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<Student> findAll();

    Mono<Student> add(String name, String surname, Integer age);
}

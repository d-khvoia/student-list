package com.freelance.studentlist.service;

import com.freelance.studentlist.configuration.R2DBCConfiguration;
import com.freelance.studentlist.domain.Student;

import io.r2dbc.h2.H2ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private H2ConnectionFactory connectionFactory = new R2DBCConfiguration().connectionFactory();

    @Autowired
    private DatabaseClient client = DatabaseClient.create(connectionFactory);

    @Override
    public Flux<Student> findAll() {

        return client.execute("SELECT * FROM student")
                     .as(Student.class).fetch().all();
    }

    @Override
    public Mono<Student> add(String name, String surname, Integer age) {

        return client.execute("INSERT INTO student (age, name, surname) VALUES(:age, :name, :surname)")
                     .bind("age", age)
                     .bind("name", name)
                     .bind("surname", surname)
                     .as(Student.class).fetch().one();
    }
}

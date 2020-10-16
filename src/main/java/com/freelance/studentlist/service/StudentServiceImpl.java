package com.freelance.studentlist.service;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    @Qualifier("jdbcScheduler")
    private Scheduler jdbcScheduler;

    @Override
    public Flux<Student> findAll() {

        Flux<Student> defer = Flux.defer(() -> Flux.fromIterable(studentRepository.findAll()));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Student> add(String name, String surname, Integer age) {

        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            Student student = new Student(name, surname, age);
            Student savedStudent = studentRepository.save(student);
            return savedStudent;
        })).subscribeOn(jdbcScheduler);
    }
}

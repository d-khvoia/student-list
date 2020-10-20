package com.freelance.studentlist.repository;

import com.freelance.studentlist.domain.Student;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

}

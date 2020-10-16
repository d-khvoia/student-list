package com.freelance.studentlist.repository;

import com.freelance.studentlist.domain.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}

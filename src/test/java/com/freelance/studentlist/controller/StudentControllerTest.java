package com.freelance.studentlist.controller;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.service.StudentService;
import com.freelance.studentlist.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.support.TransactionTemplate;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    TransactionTemplate transactionTemplate;


    @MockBean
    private StudentService studentService;

    @Test
    public void testList() {
        Student student = new Student("TestName", "TestSurname", 21);

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);

        Flux<Student> studentFlux = Flux.fromIterable(studentList);

        Mockito
                .when(studentService.findAll())
                .thenReturn(studentFlux);

        webTestClient.get().uri("/")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Student.class);
    }

    @Test
    public void testAdd() {
        Student student = new Student("TestName2", "TestSurname2", 19);

        Mockito
                .when(studentService.add("TestName2", "TestSurname2", 19))
                .thenReturn(Mono.just(student));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/add/")
                                .queryParam("name", "TestName2")
                                .queryParam("surname", "TestSurname2")
                                .queryParam("age", 19)
                                .build())
                .exchange()
                .expectStatus().isOk();
    }
}

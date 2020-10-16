package com.freelance.studentlist.config;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {

        return args -> {

            log.info("Preloading " + studentRepository.save(new Student("Ivan", "Petrov", 18)));
            log.info("Preloading " + studentRepository.save(new Student("Petr", "Ivanov", 20)));
            log.info("Preloading " + studentRepository.save(new Student("Sergei", "Sidorov", 17)));
            log.info("Preloading " + studentRepository.save(new Student("Dmitri", "Fedorenko", 16)));
            log.info("Preloading " + studentRepository.save(new Student("Edinstvennaya", "Feministka", 16)));
        };
    }
}

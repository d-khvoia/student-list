package com.freelance.studentlist;

import com.freelance.studentlist.domain.Student;
import com.freelance.studentlist.repository.StudentRepository;

import java.time.Duration;
import java.util.Arrays;

import io.r2dbc.spi.ConnectionFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

@SpringBootApplication
public class StudentListApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentListApplication.class, args);
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {

        return (args) -> {
            // save five students
            studentRepository.saveAll(Arrays.asList(new Student("Ivan", "Petrov", 18),
                    new Student("Petr", "Ivanov", 20),
                    new Student("Sergei", "Sidorov", 17),
                    new Student("Dmitri", "Fedorenko", 16),
                    new Student("Edinstvennaya", "Feministka", 16)))
                    .blockLast(Duration.ofSeconds(10));
        };
    }
}

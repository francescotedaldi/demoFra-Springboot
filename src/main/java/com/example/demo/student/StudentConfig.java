package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Student mariam = new Student(
                    1L,
                    "Mariam",
                    "m.f@gmail.com",
                    LocalDate.of(2000, Month.APRIL, 5)
            );

            Student pippo = new Student(
                    2L,
                    "Pippo",
                    "p.solo@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 18)
            );

            studentRepository.saveAll(
                    List.of(mariam, pippo));
        };
    }
}

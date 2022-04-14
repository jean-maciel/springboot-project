package com.practice.simpleRest.model.configs;

import com.practice.simpleRest.model.entities.Student;
import com.practice.simpleRest.model.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student alice = new Student(1L, "Alice", 21, "Computer Science");
            Student delilah = new Student("Delilah", 21, "Computer Science");
            repository.saveAll(List.of(delilah, alice));
        };
    }
}

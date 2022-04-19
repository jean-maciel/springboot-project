package com.practice.simpleRest.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String course;

    public Student() {
    }

    public Student(String name, String email, Integer age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public Student(Long id, String name, String email, Integer age, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", courses=" + course + '}';
    }
}

package com.practice.simpleRest.controllers;

import com.practice.simpleRest.model.entities.Student;
import com.practice.simpleRest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<Student>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        return new ResponseEntity<Student>(studentService.updateStudent(studentId, name, email), HttpStatus.OK);
    }
}

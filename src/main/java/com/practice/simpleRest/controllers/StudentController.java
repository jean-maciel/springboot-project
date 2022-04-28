package com.practice.simpleRest.controllers;

import com.practice.simpleRest.exception.EmailAlreadyTakenException;
import com.practice.simpleRest.exception.StudentIdDoesNotExistException;
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
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) throws EmailAlreadyTakenException {
        return new ResponseEntity<Student>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId) throws StudentIdDoesNotExistException {
        Student deleteStudent = studentService.deleteStudent(studentId);
        return new ResponseEntity<String>("Student deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) throws StudentIdDoesNotExistException {
        return new ResponseEntity<Student>(studentService.updateStudent(studentId, name, email), HttpStatus.OK);
    }
}

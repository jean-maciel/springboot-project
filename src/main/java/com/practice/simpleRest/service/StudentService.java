package com.practice.simpleRest.service;

import com.practice.simpleRest.exception.EmailAlreadyTakenException;
import com.practice.simpleRest.exception.StudentIdDoesNotExistException;
import com.practice.simpleRest.model.entities.Student;
import com.practice.simpleRest.model.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) throws EmailAlreadyTakenException {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            log.error("Error adding new student.");
            throw new EmailAlreadyTakenException();
        }
        studentRepository.save(student);
        return student;
    }

    public Student deleteStudent(Long studentId) throws StudentIdDoesNotExistException {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new StudentIdDoesNotExistException();
        }
        Student deletedStudent = studentRepository.findById(studentId).get();
        studentRepository.deleteById(studentId);
        return deletedStudent;
    }

    @Transactional
    public Student updateStudent(Long studentId, String name, String email) throws StudentIdDoesNotExistException {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentIdDoesNotExistException::new);

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
        Student updatedStudent = studentRepository.findById(studentId).get();
        return updatedStudent;
    }
}

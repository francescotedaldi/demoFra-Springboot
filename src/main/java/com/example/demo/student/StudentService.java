package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//business layer

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findByStudentEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email già associata" +
                    " ad uno studente");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long StudentId) {
        boolean exists = studentRepository.existsById(StudentId);

        if (!exists) {
            throw new IllegalStateException("impossibile eliminare lo studente " +
                    "con id " + StudentId + " perchè non esiste");
        }
        studentRepository.deleteById(StudentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(
                studentId).orElseThrow(() -> new IllegalStateException(
                "Lo studente con id " + studentId + " non esiste"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional =
                    studentRepository.findByStudentEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email già associata ad uno studente");
            }
            student.setEmail(email);
        }
    }
}

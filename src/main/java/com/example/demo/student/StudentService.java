package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByName = studentRepository.findStudentByName(student.getName());
        if(studentByName.isPresent()){
            try {
                throw new Exception("name taken");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new RuntimeException("student with id" + studentId + " does not exist.");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException(
                "student with Id" + studentId + " does not exist"
                )
        );
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);

        }

    }


    public Optional<Student> getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException(
                        "student with Id" + studentId + " does not exist"
                )
        );
        return studentRepository.findById(studentId);

    }
}

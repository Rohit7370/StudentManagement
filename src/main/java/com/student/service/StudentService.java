package com.student.service;

import com.student.entity.Result;
import com.student.entity.Student;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional  // Ensures atomicity: both Student & Address are saved together or rolled back
    public Student saveStudent(Student student) {

        if (student.getAddress() == null || student.getResult()==null) {
            throw new NullPointerException("Address and Result is required to save a student.");
        }
        Result result=student.getResult();
        result.setTotal(result.getHindi()+result.getEnglish()+result.getMaths());
        System.out.println("total marks is : "+result.getTotal());
        result.setAverage(result.getTotal()/3.0);
        return studentRepository.save(student); // Saves both Student & Address due to CascadeType.ALL
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by id
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    public Optional<Student> getStudentResultById(Long id) {
        return studentRepository.getResultById(id);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student with ID " + id + " not found.");
        }
    }
}


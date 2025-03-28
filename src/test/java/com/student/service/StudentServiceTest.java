package com.student.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.student.entity.Student;
import com.student.entity.Address;
import com.student.entity.Result;
import com.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent_Success() {
        // Arrange
        Student student = new Student();
        student.setName("John Doe");

        Address address = new Address();
        address.setCity("New York");
        student.setAddress(address);

        Result result = new Result();
        result.setHindi(80);
        result.setEnglish(90);
        result.setMaths(70);
        student.setResult(result);

        // Expected total and average
        int expectedTotal = 80 + 90 + 70;
        double expectedAverage = expectedTotal / 3.0;

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        Student savedStudent = studentService.saveStudent(student);

        // Assert
        assertNotNull(savedStudent);
        assertEquals(expectedTotal, savedStudent.getResult().getTotal());
        assertEquals(expectedAverage, savedStudent.getResult().getAverage(), 0.01);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testSaveStudent_ThrowsException_WhenAddressIsNull() {
        // Arrange
        Student student = new Student();
        student.setName("Jane Doe");

        Result result = new Result();
        result.setHindi(75);
        result.setEnglish(85);
        result.setMaths(95);
        student.setResult(result);
        student.setAddress(null); // Missing address

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> {
            studentService.saveStudent(student);
        });

        assertEquals("Address and Result are required to save a student.", exception.getMessage());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testSaveStudent_ThrowsException_WhenResultIsNull() {
        // Arrange
        Student student = new Student();
        student.setName("Alice");

        Address address = new Address();
        address.setCity("Los Angeles");
        student.setAddress(address);
        student.setResult(null); // Missing result

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> {
            studentService.saveStudent(student);
        });

        assertEquals("Address and Result are required to save a student.", exception.getMessage());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testSaveStudent_TotalMarksCalculation() {
        // Arrange
        Student student = new Student();
        student.setName("Michael");

        Address address = new Address();
        address.setCity("Chicago");
        student.setAddress(address);

        Result result = new Result();
        result.setHindi(60);
        result.setEnglish(70);
        result.setMaths(80);
        student.setResult(result);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        Student savedStudent = studentService.saveStudent(student);

        // Assert
        assertEquals(210, savedStudent.getResult().getTotal());
        assertEquals(70.0, savedStudent.getResult().getAverage(), 0.01);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testSaveStudent_HandlesZeroMarksCorrectly() {
        // Arrange
        Student student = new Student();
        student.setName("Emma");

        Address address = new Address();
        address.setCity("Boston");
        student.setAddress(address);

        Result result = new Result();
        result.setHindi(0);
        result.setEnglish(0);
        result.setMaths(0);
        student.setResult(result);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        Student savedStudent = studentService.saveStudent(student);

        // Assert
        assertEquals(0, savedStudent.getResult().getTotal());
        assertEquals(0.0, savedStudent.getResult().getAverage(), 0.01);
        verify(studentRepository, times(1)).save(student);
    }
}

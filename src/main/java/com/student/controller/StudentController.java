package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Controller", description = "Manage students in the database system")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Create or Update student
    @PostMapping
    @Operation(method = "createOrUpdateStudent",
            summary = "Create or Update a Student",
            description = "Adds a new student to the system or updates an existing one.",
            requestBody = @RequestBody(
                    description = "Student object that needs to be added or updated",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Student successfully created",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)) }),
                    @ApiResponse(responseCode = "400", description = "Invalid input - Validation failed",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<Student> createOrUpdateStudent(@Valid @org.springframework.web.bind.annotation.RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Get all students
    @GetMapping
    @Operation(method = "getAllStudents",
            summary = "Get all students",
            description = "Fetch a list of all the students."
    )
    @ApiResponse(responseCode = "200", description = "List of all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get student by id
    @GetMapping("/{id}")
    @Operation(method = "getStudentById",
            summary = "Get the student based on his id",
            description = "Fetch the student based on his id...!!."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> getStudentById( @Parameter(description = "ID of the student", example = "1", required = true)
            @PathVariable("id") Long id) throws NoHandlerFoundException {

        Optional<Student> student = studentService.getStudentById(id);
        System.out.println("student fetched by id");
        if (student.isEmpty()) {
            throw new NoHandlerFoundException("GET", "/students/id", null);
        }
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete student by id
    @DeleteMapping("/{id}")
    @Operation(method = "deleteStudent",
            summary = "Delete the student",
            description = "Delete the student from database based on his id....!!"
    )
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/result")
    @Operation(method = "getStudentResultById",
            summary = "Get result of the student based on his id...!!",
            description = "Fetch a list of all the students."
    )
    public ResponseEntity<Student> getStudentResultById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.getStudentResultById(id);
        System.out.println("student result fetched by id");
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.student.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.NotFound;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "students")
@Schema(description = "Student entity")
public class Student {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Student id", example ="1")
    private Long id;

    @NotEmpty(message = "Name must not be empty...!!")
    @Schema(description = "Student name", example ="Rohit Kumar")
    private String name;

    @Schema(description = "Student email", example ="rohit@gmail.com")
    @Email(message = "Enter a valid email...!!")
    private String email;

    @Schema(description = "Course selected by the student", example ="java")
    private String course;

    // One-to-one relationship with Address
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @Schema(description = "Addres of the Student", example ="address id, city, street, pin")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "resultId")
    @Schema(description = "Student's result and its total marks with average", example ="hindi, english, maths..")
    private Result result;


}

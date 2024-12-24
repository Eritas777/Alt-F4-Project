package ru.profileplus.profileplusproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemId;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String motherName;
    private String motherPhoneNumber;
    private String fatherName;
    private String fatherPhoneNumber;
    private String residentialAdress;
    private String phoneNumber;
    private String institutionID;
    private String role;
    private String studyGroup;
    private String academicDiscipline;
}

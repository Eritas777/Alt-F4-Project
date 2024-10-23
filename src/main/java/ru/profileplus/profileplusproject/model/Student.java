package ru.profileplus.profileplusproject.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue
	private Long systemID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String motherName;
	private String motherPhoneNumber;
	private String fatherName;
	private String fatherPhoneNumber;
	@Column(unique = true)
	private String student_email;
	private String residentialAdress;
	private String phoneNumber;
	private String institutionID;
}

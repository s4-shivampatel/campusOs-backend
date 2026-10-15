package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.Gender;
import com.campusos.campusos.common.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentRequest {

    //<--------USER Details----------->
    @NotBlank(message = "username is required")
    @Column(nullable = false, length = 50)
    private String username;

    @NotBlank(message = "email is required")
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "password should not be blanked")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "password should not be blanked")
    @Column(nullable = false)
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    //<--------Student Details----------->
    @NotBlank(message = "Student ID is required")
    @Size(max = 50, message = "Student ID must not exceed 50 characters")
    private String studentId;

    @NotBlank(message = "Roll number is required")
    @Size(max = 50, message = "Roll number must not exceed 50 characters")
    private String rollNumber;

    @NotBlank(message = "Admission number is required")
    @Size(max = 50, message = "Admission number must not exceed 50 characters")
    private String admissionNumber;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date cannot be in the future")
    private LocalDate admissionDate;
}

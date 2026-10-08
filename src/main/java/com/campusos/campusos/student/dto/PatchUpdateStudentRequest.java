package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatchUpdateStudentRequest {

    @Size(max = 50)
    private String rollNumber;

    @Size(max = 100)
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private Gender gender;

    @PastOrPresent(message = "Admission date cannot be in the future")
    private LocalDate admissionDate;
}

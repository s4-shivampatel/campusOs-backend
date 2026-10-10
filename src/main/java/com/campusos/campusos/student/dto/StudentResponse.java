package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentResponse {
    private Long id;
    private String studentId;
    private String rollNumber;
    private String admissionNumber;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
    private LocalDate admissionDate;
    private String status;
    private Long userId;
    private Long departmentId;
    private StudentProfileResponse studentProfile;
    private List<StudentAddressResponse> addresses;
    private List<StudentGuardianResponse> guardians;
    private List<StudentDocumentResponse> documents;
    private List<StudentEnrollmentResponse> enrollments;
}
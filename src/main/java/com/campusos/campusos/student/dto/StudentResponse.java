package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.Gender;
import com.campusos.campusos.user.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private User user;
//    private Long departmentId;
    private StudentProfileResponse studentProfile;
    private List<StudentAddressResponse> addresses;
    private List<StudentGuardianResponse> guardians;
    private List<StudentDocumentResponse> documents;
    private List<StudentEnrollmentResponse> enrollments;
}
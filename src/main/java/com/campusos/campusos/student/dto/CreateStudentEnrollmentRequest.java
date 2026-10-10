package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentEnrollmentRequest {

//    @NotNull(message = "Student ID is required")
//    private Long studentId;

//    @NotNull(message = "Department ID is required")
//    private Long departmentId;
//
//    @NotNull(message = "Program ID is required")
//    private Long programId;
//
//    @NotNull(message = "Batch ID is required")
//    private Long batchId;

    private LocalDate enrollmentDate;

    private Integer semester;

    private Integer year;

    private EnrollmentStatus status;
}
package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.EnrollmentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentEnrollmentResponse {

    private Long id;

    private Long studentId;

    private Long departmentId;

    private Long programId;

    private Long batchId;

    private LocalDate enrollmentDate;

    private Integer semester;

    private Integer year;

    private EnrollmentStatus status;

    private List<Long> courses;
}
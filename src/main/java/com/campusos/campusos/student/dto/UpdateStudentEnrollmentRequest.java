package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStudentEnrollmentRequest {

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @NotNull(message = "Program ID is required")
    private Long programId;

    @NotNull(message = "Batch ID is required")
    private Long batchId;

    private Integer semester;

    private Integer year;

    private EnrollmentStatus status;
}
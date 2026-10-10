package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.EnrollmentStatus;
import lombok.Data;

@Data
public class PatchUpdateEnrollmentRequest {
    private Long departmentId;
    private Long programId;
    private Long batchId;
    private Integer semester;
    private Integer year;
    private EnrollmentStatus status;
}

package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStudentCourseRequest {

    @NotNull(message = "Enrollment ID is required")
    private Long enrollmentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Semester is required")
    private Integer semester;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
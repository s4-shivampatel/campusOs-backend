package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStudentCourseRequest {

    @NotNull(message = "Semester is required")
    private Integer semester;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
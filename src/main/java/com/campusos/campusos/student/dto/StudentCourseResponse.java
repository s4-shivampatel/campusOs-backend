package com.campusos.campusos.student.dto;

import lombok.Data;

@Data
public class StudentCourseResponse {

    private Long id;

    private Long enrollmentId;

    private Long courseId;

    private Integer semester;

    private Boolean active;
}

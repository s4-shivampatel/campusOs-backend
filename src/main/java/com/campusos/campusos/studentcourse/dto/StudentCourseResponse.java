
package com.campusos.campusos.studentcourse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseResponse {

    private Long id;

    private Long enrollmentId;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Integer credits;

    private Long teacherId;
    private String faculty;

    private Integer semester;
    private Boolean active;
    private LocalDateTime enrolledAt;
}

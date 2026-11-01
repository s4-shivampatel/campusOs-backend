
package com.campusos.campusos.studentcourse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentCourseRequest {

    @NotNull
    private Long enrollmentId;

    @NotNull
    private Long courseId;

    private Long teacherId;

    @NotNull
    @Min(1)
    private Integer semester;
}


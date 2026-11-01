
package com.campusos.campusos.studentcourse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentCourseRequest {

    private Long teacherId;

    @NotNull
    @Min(1)
    private Integer semester;

    @NotNull
    private Boolean active;
}


package com.campusos.campusos.studentcourse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentCourseProgressRequest {

    @NotNull
    @Min(0)
    private Integer completedTopics;

    @NotNull
    @Min(0)
    private Integer totalTopics;
}

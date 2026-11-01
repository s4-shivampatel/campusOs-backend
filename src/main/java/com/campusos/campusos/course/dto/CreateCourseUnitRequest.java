
package com.campusos.campusos.course.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseUnitRequest {

    @NotNull
    private Long courseId;

    @NotNull
    @Min(1)
    private Integer unitNumber;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String description;
}

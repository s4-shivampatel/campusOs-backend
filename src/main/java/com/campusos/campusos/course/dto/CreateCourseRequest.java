
package com.campusos.campusos.course.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseRequest {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String code;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer credits;

    @Size(max = 1000)
    private String description;
}

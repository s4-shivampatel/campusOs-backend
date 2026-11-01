
package com.campusos.campusos.course.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseMaterialRequest {

    @NotNull
    private Long courseId;

    private Long unitId;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 1000)
    private String fileUrl;

    @NotBlank
    @Size(max = 30)
    private String fileType;

    @Size(max = 500)
    private String description;
}

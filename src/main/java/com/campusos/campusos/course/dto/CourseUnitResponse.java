
package com.campusos.campusos.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUnitResponse {

    private Long id;
    private Long courseId;
    private Integer unitNumber;
    private String title;
    private String description;
}


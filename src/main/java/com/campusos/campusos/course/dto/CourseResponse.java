
package com.campusos.campusos.course.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String name;
    private String code;
    private Integer credits;
    private String description;
    private Boolean active;
    private LocalDateTime createdAt;
}

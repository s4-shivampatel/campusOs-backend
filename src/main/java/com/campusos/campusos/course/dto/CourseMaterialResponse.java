
package com.campusos.campusos.course.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseMaterialResponse {

    private Long id;
    private Long courseId;
    private Long unitId;
    private String name;
    private String fileUrl;
    private String fileType;
    private String description;
    private LocalDateTime uploadedAt;
}

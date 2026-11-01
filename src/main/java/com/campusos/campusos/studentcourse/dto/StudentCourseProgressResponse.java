
package com.campusos.campusos.studentcourse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseProgressResponse {

    private Long id;
    private Long studentCourseId;
    private Integer completedTopics;
    private Integer totalTopics;
    private Integer progress;
    private LocalDateTime lastAccessedAt;
}

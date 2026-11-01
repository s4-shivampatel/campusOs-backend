
package com.campusos.campusos.assignment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentResponse {

    private Long id;
    private Long courseId;
    private String courseName;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private BigDecimal totalMarks;
    private Boolean active;
    private LocalDateTime createdAt;
}


package com.campusos.campusos.assignment.dto;

import com.campusos.campusos.assignment.entity.AssignmentSubmissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentSubmissionResponse {

    private Long id;

    private Long assignmentId;
    private String assignmentTitle;

    private Long enrollmentId;

    private String fileUrl;
    private LocalDateTime submittedAt;

    private AssignmentSubmissionStatus status;

    private BigDecimal marksObtained;
    private BigDecimal totalMarks;

    private String feedback;
    private LocalDateTime createdAt;
}

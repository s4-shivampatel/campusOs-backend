
package com.campusos.campusos.assignment.dto;

import com.campusos.campusos.assignment.entity.AssignmentSubmissionStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateAssignmentSubmissionRequest {

    @Size(max = 1000)
    private String fileUrl;

    private AssignmentSubmissionStatus status;

    @DecimalMin("0.00")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal marksObtained;

    @Size(max = 2000)
    private String feedback;
}

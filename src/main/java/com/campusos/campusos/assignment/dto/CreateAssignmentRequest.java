
package com.campusos.campusos.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAssignmentRequest {

    @NotNull
    private Long courseId;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 3000)
    private String description;

    @NotNull
    @Future
    private LocalDateTime deadline;

    @NotNull
    @DecimalMin("0.01")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal totalMarks;
}

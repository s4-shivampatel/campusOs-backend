
package com.campusos.campusos.assignment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAssignmentSubmissionRequest {

    @NotNull
    private Long assignmentId;

    @Size(max = 1000)
    private String fileUrl;
}

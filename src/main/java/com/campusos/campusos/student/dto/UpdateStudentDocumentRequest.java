package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStudentDocumentRequest {

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type must not exceed 50 characters")
    private String documentType;

    @NotBlank(message = "Document number is required")
    @Size(max = 100, message = "Document number must not exceed 100 characters")
    private String documentNumber;

    @NotBlank(message = "File URL is required")
    @Size(max = 500, message = "File URL must not exceed 500 characters")
    private String fileUrl;
}
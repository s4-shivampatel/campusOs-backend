package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatchUpdateStudentDocReq {
    @Size(max = 50, message = "Document type must not exceed 50 characters")
    private String documentType;

    @Size(max = 100, message = "Document number must not exceed 100 characters")
    private String documentNumber;

    @Size(max = 500, message = "File URL must not exceed 500 characters")
    private String fileUrl;
}

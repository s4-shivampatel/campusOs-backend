package com.campusos.campusos.student.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDocumentResponse {

    private Long id;

    private String documentType;

    private String documentNumber;

    private String fileUrl;

    private LocalDateTime uploadedAt;

    private Long studentId;
}
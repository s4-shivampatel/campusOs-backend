package com.campusos.campusos.student.dto;

import com.campusos.campusos.student.entity.Student;
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
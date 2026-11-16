package com.campusos.campusos.auth.dto;

import lombok.Data;

@Data
public class StudentLoginResponseDto {

    private Long userId;
    private Long studentId;
    private String username;
    private String message;
    private String jwtToken;
}

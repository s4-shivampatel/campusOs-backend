package com.campusos.campusos.user.dto;

import com.campusos.campusos.user.entity.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private UserRole role;

    private Boolean enabled;

    private Long studentId;

    private Long teacherId;

    private LocalDateTime createdAt;
}

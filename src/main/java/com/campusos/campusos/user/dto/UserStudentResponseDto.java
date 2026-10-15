package com.campusos.campusos.user.dto;

import com.campusos.campusos.common.enums.UserStatus;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.user.entity.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserStudentResponseDto {

    private Long id;

    private String username;

    private String email;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long studentId;

    private Student student;

    private UserRole role;

}

package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.GuardianRelation;
import lombok.Data;

@Data
public class StudentGuardianResponse {

    private Long id;

    private String name;

    private GuardianRelation relation;

    private String phone;

    private String email;

    private String occupation;

    private Long studentId;
}
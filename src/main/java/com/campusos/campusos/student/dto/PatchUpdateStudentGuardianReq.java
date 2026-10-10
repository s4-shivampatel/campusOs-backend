package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.GuardianRelation;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PatchUpdateStudentGuardianReq {


    @Size(max = 100, message = "Guardian name must not exceed 100 characters")
    private String name;

    private GuardianRelation relation;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 100, message = "Occupation must not exceed 100 characters")
    private String occupation;

}

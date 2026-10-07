package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.GuardianRelation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStudentGuardianRequest {

    @NotBlank(message = "Guardian name is required")
    @Size(max = 100, message = "Guardian name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Guardian relation is required")
    private GuardianRelation relation;

    @NotBlank(message = "Phone number is required")
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

    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;
}

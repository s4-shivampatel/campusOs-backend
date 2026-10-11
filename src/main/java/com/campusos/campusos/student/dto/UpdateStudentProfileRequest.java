package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStudentProfileRequest {
    @NotBlank(message = "Profile photo URL is required")
    @Size(max = 500, message = "Profile photo URL must not exceed 500 characters")
    private String profilePhoto;

    @NotBlank(message = "Blood group is required")
    @Size(max = 20, message = "Blood group must not exceed 20 characters")
    private String bloodGroup;

    @NotBlank(message = "Nationality is required")
    @Size(max = 50, message = "Nationality must not exceed 50 characters")
    private String nationality;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @NotBlank(message = "Mother tongue is required")
    @Size(max = 50, message = "Mother tongue must not exceed 50 characters")
    private String motherTongue;

    @NotBlank(message = "Identification mark is required")
    @Size(max = 255, message = "Identification mark must not exceed 255 characters")
    private String identificationMark;

    @NotBlank(message = "Bio is required")
    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
    private String bio;

}

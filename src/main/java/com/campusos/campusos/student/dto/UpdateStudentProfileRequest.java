package com.campusos.campusos.student.dto;

import jakarta.validation.constraints.Size;

public class UpdateStudentProfileRequest {

    @Size(max = 500, message = "Profile photo URL must not exceed 500 characters")
    private String profilePhoto;

    @Size(max = 20, message = "Blood group must not exceed 20 characters")
    private String bloodGroup;

    @Size(max = 50, message = "Nationality must not exceed 50 characters")
    private String nationality;

    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @Size(max = 50, message = "Mother tongue must not exceed 50 characters")
    private String motherTongue;

    @Size(max = 255, message = "Identification mark must not exceed 255 characters")
    private String identificationMark;

    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
    private String bio;

}

package com.campusos.campusos.student.dto;

import lombok.Data;

@Data
public class StudentProfileResponse {

    private Long id;

    private String profilePhoto;

    private String bloodGroup;

    private String nationality;

    private String category;

    private String motherTongue;

    private String identificationMark;

    private String bio;

    private Long studentId;
}
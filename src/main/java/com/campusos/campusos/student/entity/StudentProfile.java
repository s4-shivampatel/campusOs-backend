package com.campusos.campusos.student.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profilePhoto;

    private String bloodGroup;

    private String nationality;

    private String category;

    private String motherTongue;

    private String identificationMark;

    @Column(length = 1000)
    private String bio;

    // StudentProfile -------> Student = 1:1

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(
            name = "student_id",
            nullable = false,
            unique = true
    )
    private Student student;
}
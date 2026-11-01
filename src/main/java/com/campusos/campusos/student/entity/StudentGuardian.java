package com.campusos.campusos.student.entity;

import com.campusos.campusos.common.enums.GuardianRelation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_guardians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGuardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private GuardianRelation relation;

    private String phone;

    private String email;

    private String occupation;



    // Many guardians can belong to one student
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false
    )
    private Student student;
}
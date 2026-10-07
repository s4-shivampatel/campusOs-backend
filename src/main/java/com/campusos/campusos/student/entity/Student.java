package com.campusos.campusos.student.entity;

//import com.campusos.campusos.department.entity.Department;
import com.campusos.campusos.common.enums.Gender;
import com.campusos.campusos.common.enums.StudentStatus;
import com.campusos.campusos.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_student_student_id", columnNames = "student_id"),
                @UniqueConstraint(name = "uk_student_roll_number", columnNames = "roll_number"),
                @UniqueConstraint(name = "uk_student_email", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false, unique = true, length = 50)
    private String studentId;

    @Column(name = "roll_number", unique = true, length = 50)
    private String rollNumber;

    @Column(name = "admission_number", unique = true, length = 50)
    private String admissionNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    private LocalDate admissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StudentStatus status;


    // <------USER RELATION------>
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            unique = true,
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_student_user")
    )
    private User user;

    // <------DEPARTMENT RELATION------>

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private Department department;


    // <------PROFILE RELATION------>
    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private StudentProfile studentProfile;


    // <------ADDRESS RELATION------>
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<StudentAddress> addresses = new ArrayList<>();


    // <------GUARDIAN RELATION------>
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<StudentGuardian> guardians = new ArrayList<>();


    // <------DOCUMENT RELATION------>
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<StudentDocument> documents = new ArrayList<>();

    // <------ENROLLMENT RELATION------>
//
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<StudentEnrollment> enrollments = new ArrayList<>();
}
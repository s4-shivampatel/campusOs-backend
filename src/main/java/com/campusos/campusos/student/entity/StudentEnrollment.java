package com.campusos.campusos.student.entity;

//import com.campusos.campusos.batch.entity.Batch;
//import com.campusos.campusos.department.entity.Department;
//import com.campusos.campusos.program.entity.Program;
import com.campusos.campusos.common.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate enrollmentDate;

    private Integer semester;

    private Integer year;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EnrollmentStatus status;


    // <------STUDENT RELATION------>
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false
    )
    private Student student;


    // =========================
    // DEPARTMENT
    // =========================

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "department_id",
//            nullable = false
//    )
//    private Department department;


    // =========================
    // PROGRAM
    // =========================

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "program_id",
//            nullable = false
//    )
//    private Program program;


    // =========================
    // BATCH
    // =========================

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "batch_id",
//            nullable = false
//    )
//    private Batch batch;



    // =========================
    // COURSES
    // =========================

//    @OneToMany(
//            mappedBy = "enrollment",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @Builder.Default
//    private List<StudentCourse> courses = new ArrayList<>();
}

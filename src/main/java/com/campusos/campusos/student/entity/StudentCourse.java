package com.campusos.campusos.student.entity;

//import com.campusos.campusos.course.entity.Course;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "student_courses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_student_course_enrollment_course",
                        columnNames = {
                                "enrollment_id",
                                "course_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // =========================
    // ENROLLMENT
    // =========================

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "enrollment_id",
//            nullable = false
//    )
//    private StudentEnrollment enrollment;


    // =========================
    // COURSE
    // =========================

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "course_id",
//            nullable = false
//    )
//    private Course course;


    // =========================
    // COURSE DETAILS
    // =========================

    private Integer semester;

    private Boolean active;
}
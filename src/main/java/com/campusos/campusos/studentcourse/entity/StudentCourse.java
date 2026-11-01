
package com.campusos.campusos.studentcourse.entity;

import com.campusos.campusos.course.entity.Course;
import com.campusos.campusos.student.entity.StudentEnrollment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "student_courses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_enrollment_course",
                        columnNames = {"enrollment_id", "course_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private StudentEnrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime enrolledAt;

    @PrePersist
    protected void onCreate() {
        enrolledAt = LocalDateTime.now();

        if (active == null) {
            active = true;
        }
    }
}

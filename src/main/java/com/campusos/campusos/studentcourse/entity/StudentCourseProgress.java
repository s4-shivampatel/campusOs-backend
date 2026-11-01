
package com.campusos.campusos.studentcourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "student_course_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_progress_student_course",
                        columnNames = "student_course_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class StudentCourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_course_id", nullable = false, unique = true)
    private StudentCourse studentCourse;

    @Column(nullable = false)
    private Integer completedTopics = 0;

    @Column(nullable = false)
    private Integer totalTopics = 0;

    @Column(nullable = false)
    private Integer progress = 0;

    private LocalDateTime lastAccessedAt;

    @PrePersist
    @PreUpdate
    protected void updateProgress() {
        if (completedTopics == null || totalTopics == null
                || completedTopics < 0 || totalTopics < 0
                || completedTopics > totalTopics) {
            throw new IllegalStateException("Invalid course topic progress");
        }

        progress = totalTopics == 0
                ? 0
                : (int) Math.round(
                completedTopics * 100.0 / totalTopics
        );
    }
}

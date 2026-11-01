
package com.campusos.campusos.assignment.entity;

import com.campusos.campusos.student.entity.StudentEnrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "assignment_submissions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_assignment_enrollment",
                        columnNames = {"assignment_id", "enrollment_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private StudentEnrollment enrollment;

    @Column(name = "file_url", length = 1000)
    private String fileUrl;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AssignmentSubmissionStatus status =
            AssignmentSubmissionStatus.PENDING;

    @Column(name = "marks_obtained", precision = 7, scale = 2)
    private BigDecimal marksObtained;

    @Column(length = 2000)
    private String feedback;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = AssignmentSubmissionStatus.PENDING;
        }
    }
}

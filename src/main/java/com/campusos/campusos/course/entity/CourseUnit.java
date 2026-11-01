
package com.campusos.campusos.course.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "course_units",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_course_unit_number",
                        columnNames = {"course_id", "unit_number"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class CourseUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "unit_number", nullable = false)
    private Integer unitNumber;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 2000)
    private String description;
}


package com.campusos.campusos.course.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_course_code", columnNames = "code")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 30, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer credits;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("unitNumber ASC")
    private List<CourseUnit> units = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CourseMaterial> materials = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (active == null) {
            active = true;
        }
    }
}

package com.campusos.campusos.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "permissions",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_permissions_name", columnNames = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (enabled == null) {
            enabled = true;
        }
    }

    @OneToMany(mappedBy = "permission",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<RolePermission> rolePermission;
}
package com.campusos.campusos.student.entity;

import com.campusos.campusos.common.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AddressType type;

    @Column(nullable = false, length = 300)
    private String addressLine;

    private String city;

    private String district;

    private String state;

    private String country;

    private String pincode;


//  Many addresses can belong to one student

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false,
            unique = true
    )
    private Student student;
}
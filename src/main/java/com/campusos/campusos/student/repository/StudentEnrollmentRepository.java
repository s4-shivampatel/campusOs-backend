package com.campusos.campusos.student.repository;

import com.campusos.campusos.student.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
}

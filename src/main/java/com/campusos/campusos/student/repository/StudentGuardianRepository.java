package com.campusos.campusos.student.repository;

import com.campusos.campusos.student.entity.StudentGuardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGuardianRepository extends JpaRepository<StudentGuardian,Long> {
}

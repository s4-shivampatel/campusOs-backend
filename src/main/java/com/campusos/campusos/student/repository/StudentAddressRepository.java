package com.campusos.campusos.student.repository;

import com.campusos.campusos.student.entity.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress,Long> {
}

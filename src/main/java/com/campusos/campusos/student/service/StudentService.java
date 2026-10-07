package com.campusos.campusos.student.service;


import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public StudentResponse createStudent(@Valid CreateStudentRequest createDto) {
        return
    }

    public StudentResponse getStudentById(Long id) {

    }

    public List<StudentResponse> getAllStudents() {
    }

    public StudentResponse updateStudent(Long id, @Valid CreateStudentRequest createDto) {
    }

    public void deleteStudent(Long id) {
    }
}

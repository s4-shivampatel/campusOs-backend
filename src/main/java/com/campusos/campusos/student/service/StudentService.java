package com.campusos.campusos.student.service;


import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.campusos.campusos.common.enums.StudentStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public StudentResponse createStudent(@Valid CreateStudentRequest createDto) {
        Student student=new Student();
        student=modelMapper.map(createDto, Student.class);
        if(student.getStatus()==null){
            student.setStatus(ACTIVE);
        }
        Student savedStudent=studentRepository.save(student);

        return modelMapper.map(savedStudent,StudentResponse.class);
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

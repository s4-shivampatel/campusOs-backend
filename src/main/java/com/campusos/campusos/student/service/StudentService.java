package com.campusos.campusos.student.service;


import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import com.campusos.campusos.student.dto.UpdateStudentRequest;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        Student  savedStudent=studentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Student not found with id: " + id)
        );
        return modelMapper.map(savedStudent,StudentResponse.class);

    }

    public List<StudentResponse> getAllStudents() {
        List<Student> savedStudents=studentRepository.findAll();
        List<StudentResponse> response=new ArrayList<>();
        for(Student student:savedStudents){
            response.add(modelMapper.map(student,StudentResponse.class));
        }
        return response;
    }

    public StudentResponse updateStudent(Long id, @Valid UpdateStudentRequest updateDto) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("student not found with id:"+id)
        );
        modelMapper.map(updateDto,studentFounded);
        Student updatedStudent=studentRepository.save(studentFounded);
        return modelMapper.map(updatedStudent,StudentResponse.class);
    }

    public void deleteStudent(Long id) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new RuntimeException("student not found with id:"+id)
        );
        studentRepository.delete(studentFounded);
    }
}

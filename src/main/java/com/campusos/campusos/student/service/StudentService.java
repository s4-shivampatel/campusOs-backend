package com.campusos.campusos.student.service;


import com.campusos.campusos.common.exception.DuplicateResourceException;
import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.PatchUpdateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import com.campusos.campusos.student.dto.UpdateStudentRequest;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.campusos.campusos.common.enums.StudentStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class StudentService {

    //@Autowired not required due to @RequiredArgsConstructor
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentResponse createStudent(@Valid CreateStudentRequest createDto) {
        if(studentRepository.existsByEmail(createDto.getEmail())){
            throw new DuplicateResourceException("Student with email '"+createDto.getEmail()+"' already exists");
        }
        if(studentRepository.existsByRollNumber(createDto.getRollNumber())){
            throw new DuplicateResourceException("Student with roll number '"+createDto.getRollNumber()+"' already exists");
        }
        Student student=modelMapper.map(createDto, Student.class);
        if(student.getStatus()==null){
            student.setStatus(ACTIVE);
        }
        Student savedStudent=studentRepository.save(student);

        return modelMapper.map(savedStudent,StudentResponse.class);
    }

    public StudentResponse getStudentById(Long id) {

        Student  savedStudent=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id: " + id)
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
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        modelMapper.map(updateDto,studentFounded);
        Student updatedStudent=studentRepository.save(studentFounded);
        return modelMapper.map(updatedStudent,StudentResponse.class);
    }

    public void deleteStudent(Long id) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        studentRepository.delete(studentFounded);
    }


    public StudentResponse patchUpdateStudent(Long id, @Valid PatchUpdateStudentRequest patchUpdateStudentRequest) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        modelMapper.map(patchUpdateStudentRequest,studentFounded);

        Student savedStudent=studentRepository.save(studentFounded);
        return modelMapper.map(savedStudent,StudentResponse.class);

    }
}

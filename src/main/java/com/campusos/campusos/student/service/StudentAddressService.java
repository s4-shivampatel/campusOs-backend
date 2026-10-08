package com.campusos.campusos.student.service;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.entity.StudentAddress;
import com.campusos.campusos.student.repository.StudentAddressRepository;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentAddressService {
    private final StudentRepository studentRepository;
    private final StudentAddressRepository studentAddressRepository;

    private final ModelMapper modelMapper;

    public StudentAddressResponse createStudentAdd(Long id,@Valid CreateStudentAddressRequest createAddDto) {
        Student student=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+id)
        );
        StudentAddress studentAddress=modelMapper.map(createAddDto,StudentAddress.class);
        studentAddress.setStudent(student);

        StudentAddress savedStudentAdd= studentAddressRepository.save(studentAddress);

        StudentAddressResponse response=modelMapper.map(savedStudentAdd,StudentAddressResponse.class);


        return response;
    }

//    public StudentAddressResponse getStudentAddById(Long id) {
//    }
//
//
//    public List<StudentAddressResponse> getAddOfStudent() {
//    }
//
//    public StudentAddressResponse updateStudentAdd(Long id, @Valid UpdateStudentAddressRequest updateAddDto) {
//    }
//
//    public StudentAddressResponse patchUpdateStudent(Long id, @Valid PatchUpdateStudentAddRequest patchUpdateStudentAddRequest) {
//    }
//
//    public void deleteStudentAdd(Long id) {
//    }
}

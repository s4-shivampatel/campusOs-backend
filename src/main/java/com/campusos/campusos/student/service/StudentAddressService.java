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

import java.util.ArrayList;
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

        return modelMapper.map(savedStudentAdd,StudentAddressResponse.class);

    }

    public StudentAddressResponse getStudentAddById(Long id) {

        StudentAddress studentAddress=studentAddressRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Address not found with id:"+id)
        );
        return modelMapper.map(studentAddress,StudentAddressResponse.class);
    }


    public List<StudentAddressResponse> getAddOfStudent() {
        List<StudentAddress> studentAddresses=studentAddressRepository.findAll();
        List<StudentAddressResponse> responses=new ArrayList<>();
        for (StudentAddress studentAddress: studentAddresses){
            responses.add(modelMapper.map(studentAddress,StudentAddressResponse.class));
        }
        return responses;
    }

    public StudentAddressResponse updateStudentAdd(Long id, @Valid UpdateStudentAddressRequest updateAddDto) {
        StudentAddress studentAddressFounded=studentAddressRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Address not found with id;"+id)
        );
        modelMapper.map(updateAddDto,studentAddressFounded);
        StudentAddress updatedAddress=studentAddressRepository.save(studentAddressFounded);
        return modelMapper.map(updatedAddress,StudentAddressResponse.class);

    }

    public StudentAddressResponse patchUpdateStudent(Long id, @Valid PatchUpdateStudentAddRequest patchUpdateStudentAddRequest) {
        StudentAddress studentAddressFounded=studentAddressRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Address not found with id;"+id)
        );
        modelMapper.map(patchUpdateStudentAddRequest,studentAddressFounded);
        StudentAddress updatedAddress=studentAddressRepository.save(studentAddressFounded);
        return modelMapper.map(updatedAddress,StudentAddressResponse.class);
    }

    public void deleteStudentAdd(Long id) {
        StudentAddress studentAddressFounded=studentAddressRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Address not found with id;"+id)
        );
        studentAddressRepository.delete(studentAddressFounded);
    }
}

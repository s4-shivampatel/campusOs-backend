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

    public StudentAddressResponse createStudentAdd(Long studentId,@Valid CreateStudentAddressRequest createAddDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentAddress studentAddress=modelMapper.map(createAddDto,StudentAddress.class);

        //Maintaining Both side relation
        studentAddress.setStudent(student);
        List<StudentAddress> addressList=new ArrayList<>();
        addressList.add(studentAddress);
        student.setAddresses(addressList);

        StudentAddress savedStudentAdd= studentAddressRepository.save(studentAddress);

        StudentAddressResponse response=modelMapper.map(savedStudentAdd,StudentAddressResponse.class);
        response.setStudentId(studentId);
        return response;

    }

    public StudentAddressResponse getStudentAddById(Long studentId,Long addressId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()-> new ResourceNotFoundException("student not found with id:"+studentId)
        );
        StudentAddress studentAddress=studentAddressRepository.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address not found with id:"+addressId)
        );
        StudentAddressResponse response=modelMapper.map(studentAddress,StudentAddressResponse.class);
        response.setStudentId(studentId);

        return response;
    }


    public List<StudentAddressResponse> getAddOfStudent(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not exists with student id:"+studentId)
        );
        List<StudentAddress> studentAddresses=studentAddressRepository.findAll();
        List<StudentAddressResponse> responses=new ArrayList<>();
        for (StudentAddress studentAddress: studentAddresses){
            StudentAddressResponse res=modelMapper.map(studentAddress,StudentAddressResponse.class);
            res.setStudentId(studentId);
            responses.add(res);
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

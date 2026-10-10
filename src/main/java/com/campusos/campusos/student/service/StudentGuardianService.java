package com.campusos.campusos.student.service;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.entity.StudentEnrollment;
import com.campusos.campusos.student.entity.StudentGuardian;
import com.campusos.campusos.student.repository.StudentEnrollmentRepository;
import com.campusos.campusos.student.repository.StudentGuardianRepository;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGuardianService {

    private final StudentGuardianRepository studentGuardianRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public StudentGuardianResponse createStudentGuardian(Long studentId, @Valid CreateStudentGuardianRequest createGuardianDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentGuardian studentGuardian= modelMapper.map(createGuardianDto,StudentGuardian.class);
        studentGuardian.setStudent(student);

        StudentGuardian saveGuardian=studentGuardianRepository.save(studentGuardian);
        StudentGuardianResponse response=modelMapper.map(saveGuardian,StudentGuardianResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public StudentGuardianResponse getStudentGuardianById(Long studentId, Long guardianId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentGuardian guardianFounded=studentGuardianRepository.findById(guardianId).orElseThrow(
                ()->new ResourceNotFoundException("Guardian Not found with id:"+guardianId)
        );
        StudentGuardianResponse response=modelMapper.map(guardianFounded,StudentGuardianResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public List<StudentGuardianResponse> getAllStudentGuardian(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        List<StudentGuardian> allGuardian=studentGuardianRepository.findAll();
        List<StudentGuardianResponse> responses=new ArrayList<>();
        for (StudentGuardian studentGuardian:allGuardian){
            StudentGuardianResponse response=modelMapper.map(studentGuardian,StudentGuardianResponse.class);
            response.setStudentId(studentId);
            responses.add(response);
        }
        return responses;
    }

    public StudentGuardianResponse updateStudentGuardian(Long studentId, Long guardianId, @Valid UpdateStudentGuardianRequest updateGuardianDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentGuardian guardianFounded=studentGuardianRepository.findById(guardianId).orElseThrow(
                ()->new ResourceNotFoundException("Guardian Not found with id:"+guardianId)
        );
        modelMapper.map(updateGuardianDto,guardianFounded);
        StudentGuardian updatedGuardian=studentGuardianRepository.save(guardianFounded);
        StudentGuardianResponse response=modelMapper.map(updatedGuardian,StudentGuardianResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public StudentGuardianResponse patchUpdateStudentGuardian(Long studentId, Long guardianId, @Valid PatchUpdateStudentGuardianReq patchUpdateGuardianDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );

        StudentGuardian enrollmentFounded=studentGuardianRepository.findById(guardianId).orElseThrow(
                ()->new ResourceNotFoundException("Guardian Not found with id:"+guardianId)
        );
        modelMapper.map(patchUpdateGuardianDto,enrollmentFounded);
        StudentGuardian guardianUpdated=studentGuardianRepository.save(enrollmentFounded);
        StudentGuardianResponse response=modelMapper.map(guardianUpdated,StudentGuardianResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public String deleteStudentGuardian(Long studentId, Long guardianId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );

        StudentGuardian guardianFounded=studentGuardianRepository.findById(guardianId).orElseThrow(
                ()->new ResourceNotFoundException("Guardian Not found with id:"+guardianId)
        );
        studentGuardianRepository.delete(guardianFounded);
        return "Enrollment deleted successfully";
    }
}

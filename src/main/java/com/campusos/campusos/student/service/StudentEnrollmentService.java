package com.campusos.campusos.student.service;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.entity.StudentDocument;
import com.campusos.campusos.student.entity.StudentEnrollment;
import com.campusos.campusos.student.repository.StudentDocumentRepository;
import com.campusos.campusos.student.repository.StudentEnrollmentRepository;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentService {

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public StudentEnrollmentResponse createStudentEnroll(Long studentId, @Valid CreateStudentEnrollmentRequest createEnrollDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentEnrollment studentEnrollment= modelMapper.map(createEnrollDto,StudentEnrollment.class);
        studentEnrollment.setStudent(student);

        StudentEnrollment saveEnrollment=studentEnrollmentRepository.save(studentEnrollment);
        StudentEnrollmentResponse response=modelMapper.map(saveEnrollment,StudentEnrollmentResponse.class);
        response.setStudentId(saveEnrollment.getStudent().getId());
        return response;
    }

    public StudentEnrollmentResponse getStudentEnrollById(Long studentId, Long enrollId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentEnrollment enrollmentFounded=studentEnrollmentRepository.findById(enrollId).orElseThrow(
                ()->new ResourceNotFoundException("Enrollment Not found with id:"+enrollId)
        );
        StudentEnrollmentResponse response=modelMapper.map(enrollmentFounded,StudentEnrollmentResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public List<StudentEnrollmentResponse> getAllStudentEnrolls(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        List<StudentEnrollment> allEnrollments=studentEnrollmentRepository.findAll();
        List<StudentEnrollmentResponse> responses=new ArrayList<>();
        for (StudentEnrollment studentEnrollment:allEnrollments){
            StudentEnrollmentResponse response=modelMapper.map(studentEnrollment,StudentEnrollmentResponse.class);
            response.setStudentId(studentId);
            responses.add(response);
        }
        return responses;
    }

    public StudentEnrollmentResponse updateStudentEnroll(Long studentId, Long enrollId, @Valid UpdateStudentEnrollmentRequest updateEnrollDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentEnrollment enrollmentFounded=studentEnrollmentRepository.findById(enrollId).orElseThrow(
                ()->new ResourceNotFoundException("Enrollment Not found with id:"+enrollId)
        );
        modelMapper.map(updateEnrollDto,enrollmentFounded);
        StudentEnrollment updatedEnrollment=studentEnrollmentRepository.save(enrollmentFounded);
        StudentEnrollmentResponse response=modelMapper.map(updatedEnrollment,StudentEnrollmentResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public StudentEnrollmentResponse patchUpdateStudentEnroll(Long studentId, Long enrollId, @Valid PatchUpdateEnrollmentRequest patchUpdateEnrollDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );

        StudentEnrollment enrollmentFounded=studentEnrollmentRepository.findById(enrollId).orElseThrow(
                ()->new ResourceNotFoundException("Enrollment Not found with id:"+enrollId)
        );
        modelMapper.map(patchUpdateEnrollDto,enrollmentFounded);
        StudentEnrollment enrollmentUpdated=studentEnrollmentRepository.save(enrollmentFounded);
        StudentEnrollmentResponse response=modelMapper.map(enrollmentUpdated,StudentEnrollmentResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public String deleteStudentEnroll(Long studentId, Long enrollId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );

        StudentEnrollment enrollmentFounded=studentEnrollmentRepository.findById(enrollId).orElseThrow(
                ()->new ResourceNotFoundException("Enrollment Not found with id:"+enrollId)
        );
        studentEnrollmentRepository.delete(enrollmentFounded);
        return "Enrollment deleted successfully of id:"+ enrollId;
    }
}

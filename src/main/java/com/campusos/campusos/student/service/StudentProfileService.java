package com.campusos.campusos.student.service;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.CreateStudentProfileRequest;
import com.campusos.campusos.student.dto.StudentGuardianResponse;
import com.campusos.campusos.student.dto.StudentProfileResponse;
import com.campusos.campusos.student.dto.UpdateStudentProfileRequest;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.entity.StudentGuardian;
import com.campusos.campusos.student.entity.StudentProfile;
import com.campusos.campusos.student.repository.StudentGuardianRepository;
import com.campusos.campusos.student.repository.StudentProfileRepository;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;


    public StudentProfileResponse createStudentProfile(Long studentId, @Valid CreateStudentProfileRequest createProfileDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId+" you can't create his Profile")
        );
        StudentProfile studentProfile= modelMapper.map(createProfileDto,StudentProfile.class);
        studentProfile.setStudent(student);

        StudentProfile saveProfile=studentProfileRepository.save(studentProfile);
        student.setStudentProfile(saveProfile);
        StudentProfileResponse response=modelMapper.map(saveProfile,StudentProfileResponse.class);
        response.setStudentId(studentId);
        return response;
    }

    public StudentProfileResponse getProfileByStudentId(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        Long profileId=student.getStudentProfile().getId();
        StudentProfile profileFounded=studentProfileRepository.findById(profileId).orElseThrow(
                ()->new ResourceNotFoundException("Profile Not found of student id:"+student)
        );
        StudentProfileResponse response=modelMapper.map(profileFounded,StudentProfileResponse.class);
        response.setStudentId(studentId);
        return response;
    }


    public List<StudentProfileResponse> getAllStudentProfiles() {
        List<StudentProfile> allProfiles=studentProfileRepository.findAll();
        List<StudentProfileResponse> responses=new ArrayList<>();
        for (StudentProfile profile:allProfiles){
            StudentProfileResponse response=modelMapper.map(profile,StudentProfileResponse.class);
            response.setStudentId(profile.getStudent().getId());
            responses.add(response);
        }
        return responses;
    }

    public StudentProfileResponse updateStudentProfile(Long studentId, @Valid UpdateStudentProfileRequest updateProfileDto) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+studentId)
        );
        StudentProfile studentProfile=student.getStudentProfile();
        if (studentProfile == null) {
            throw new ResourceNotFoundException(
                    "Profile not found for Student ID " + studentId
            );
        }
        Long profileId=studentProfile.getId();

        StudentProfile profileFounded=studentProfileRepository.findById(profileId).orElseThrow(
                ()->new ResourceNotFoundException("Profile not Founded of Student Id "+studentId)
        );
        modelMapper.map(updateProfileDto,profileFounded);
//        profileFounded.setStudent(student);
        StudentProfile updatedProfile=studentProfileRepository.save(profileFounded);
        StudentProfileResponse response=modelMapper.map(updatedProfile,StudentProfileResponse.class);
        response.setStudentId(studentId);
        return response;
    }
}

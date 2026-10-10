package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.service.StudentGuardianService;
import com.campusos.campusos.student.service.StudentProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentProfileController {

    @Autowired
    private final StudentProfileService studentProfileService;

    @PostMapping("/student/{studentId}/profile")
    public ResponseEntity<StudentProfileResponse> createStudentProfile(
            @PathVariable Long studentId,
            @Valid @RequestBody CreateStudentProfileRequest createProfileDto) {
        StudentProfileResponse response = studentProfileService.createStudentProfile(studentId,createProfileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student/{studentId}/profile")
    public ResponseEntity<StudentProfileResponse> getProfileStudentById(
            @PathVariable Long studentId) {
        StudentProfileResponse response = studentProfileService.getProfileByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/student-profiles")
    public ResponseEntity<List<StudentProfileResponse>> getAllStudentProfiles() {
        List<StudentProfileResponse> response = studentProfileService.getAllStudentProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/student/{studentId}/profile")
    public ResponseEntity<StudentProfileResponse> updateStudentProfile(
            @PathVariable Long studentId,
            @Valid @RequestBody UpdateStudentProfileRequest updateProfileDto) {
        StudentProfileResponse response =studentProfileService.updateStudentProfile(studentId, updateProfileDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @PatchMapping("/{guardianId}")
//    public  ResponseEntity<StudentGuardianResponse>patchUpdateStudentGuardian(
//            @PathVariable Long studentId,
//            @PathVariable Long guardianId,
//            @Valid @RequestBody PatchUpdateStudentGuardianReq patchUpdateGuardianDto  ){
//        StudentGuardianResponse response=studentGuardianService.patchUpdateStudentGuardian(studentId,guardianId,patchUpdateGuardianDto);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/{guardianId}")
//    public ResponseEntity<String> deleteStudentGuardian(
//            @PathVariable Long studentId,
//            @PathVariable Long guardianId
//    ) {
//        String message=studentGuardianService.deleteStudentGuardian(studentId,guardianId);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
//    }
}
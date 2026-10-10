package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.service.StudentEnrollmentService;
import com.campusos.campusos.student.service.StudentGuardianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/guardians")
@RequiredArgsConstructor
public class StudentGuardianController {

    @Autowired
    private final StudentGuardianService studentGuardianService;

    @PostMapping
    public ResponseEntity<StudentGuardianResponse> createStudentGuardian(
            @PathVariable Long studentId,
            @Valid @RequestBody CreateStudentGuardianRequest createGuardianDto) {
        StudentGuardianResponse response = studentGuardianService.createStudentGuardian(studentId,createGuardianDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{guardianId}")
    public ResponseEntity<StudentGuardianResponse> getStudentGuardianById(
            @PathVariable Long studentId,
            @PathVariable Long guardianId) {
        StudentGuardianResponse response = studentGuardianService.getStudentGuardianById(studentId,guardianId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentGuardianResponse>> getAllStudentGuardians(@PathVariable Long studentId) {
        List<StudentGuardianResponse> response = studentGuardianService.getAllStudentGuardian(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{guardianId}")
    public ResponseEntity<StudentGuardianResponse> updateStudentGuardian(
            @PathVariable Long studentId,
            @PathVariable Long guardianId,
            @Valid @RequestBody UpdateStudentGuardianRequest updateGuardianDto) {
        StudentGuardianResponse response =studentGuardianService.updateStudentGuardian(studentId,guardianId, updateGuardianDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{guardianId}")
    public  ResponseEntity<StudentGuardianResponse>patchUpdateStudentGuardian(
            @PathVariable Long studentId,
            @PathVariable Long guardianId,
            @Valid @RequestBody PatchUpdateStudentGuardianReq patchUpdateGuardianDto  ){
        StudentGuardianResponse response=studentGuardianService.patchUpdateStudentGuardian(studentId,guardianId,patchUpdateGuardianDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{guardianId}")
    public ResponseEntity<String> deleteStudentGuardian(
            @PathVariable Long studentId,
            @PathVariable Long guardianId
    ) {
        String message=studentGuardianService.deleteStudentGuardian(studentId,guardianId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }
}
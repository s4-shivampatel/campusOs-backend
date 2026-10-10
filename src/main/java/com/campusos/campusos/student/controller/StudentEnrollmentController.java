package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.service.StudentEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/enrollment")
@RequiredArgsConstructor
public class StudentEnrollmentController {

    @Autowired
    private final StudentEnrollmentService studentEnrollmentService;

    @PostMapping
    public ResponseEntity<StudentEnrollmentResponse> createStudentEnrollment(
            @PathVariable Long studentId,
            @Valid @RequestBody CreateStudentEnrollmentRequest createEnrollDto) {
        StudentEnrollmentResponse response = studentEnrollmentService.createStudentEnroll(studentId,createEnrollDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{enrollId}")
    public ResponseEntity<StudentEnrollmentResponse> getStudentEnrollById(
            @PathVariable Long studentId,
            @PathVariable Long enrollId) {
        StudentEnrollmentResponse response = studentEnrollmentService.getStudentEnrollById(studentId,enrollId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentEnrollmentResponse>> getAllStudentEnrolls(@PathVariable Long studentId) {
        List<StudentEnrollmentResponse> response = studentEnrollmentService.getAllStudentEnrolls(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{enrollId}")
    public ResponseEntity<StudentEnrollmentResponse> updateStudentEnroll(
            @PathVariable Long studentId,
            @PathVariable Long enrollId,
            @Valid @RequestBody UpdateStudentEnrollmentRequest updateEnrollDto) {
        StudentEnrollmentResponse response =studentEnrollmentService.updateStudentEnroll(studentId,enrollId, updateEnrollDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{enrollId}")
    public  ResponseEntity<StudentEnrollmentResponse>patchUpdateStudentEnroll(
            @PathVariable Long studentId,
            @PathVariable Long enrollId,
            @Valid @RequestBody PatchUpdateEnrollmentRequest patchUpdateEnrollDto  ){
        StudentEnrollmentResponse response=studentEnrollmentService.patchUpdateStudentEnroll(studentId,enrollId,patchUpdateEnrollDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{enrollId}")
    public ResponseEntity<String> deleteStudentEnroll(
            @PathVariable Long studentId,
            @PathVariable Long enrollId
            ) {
        String message=studentEnrollmentService.deleteStudentEnroll(studentId,enrollId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }
}
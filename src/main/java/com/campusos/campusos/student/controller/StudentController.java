package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.PatchUpdateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import com.campusos.campusos.student.dto.UpdateStudentRequest;
import com.campusos.campusos.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody CreateStudentRequest CreateDto) {
        StudentResponse response = studentService.createStudent(CreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id) {
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> response = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStudentRequest updateDto) {
        StudentResponse response =studentService.updateStudent(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<StudentResponse>patchUpdateStudent(
            @PathVariable Long id,
            @Valid @RequestBody PatchUpdateStudentRequest patchUpdateStudentRequest  ){
        StudentResponse response=studentService.patchUpdateStudent(id,patchUpdateStudentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.service.StudentAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{id}/addresses")
@RequiredArgsConstructor
public class StudentAddressController {

    @Autowired
    private final StudentAddressService studentAddressService;

    @PostMapping
    public ResponseEntity<StudentAddressResponse> createStudentAdd(
            @PathVariable Long id,
            @Valid @RequestBody CreateStudentAddressRequest createAddDto) {
        StudentAddressResponse response = studentAddressService.createStudentAdd(id,createAddDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StudentAddressResponse> getStudentAddById(
//            @PathVariable Long id) {
//        StudentAddressResponse response = studentAddressService.getStudentAddById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<StudentAddressResponse>> getAddOfStudent() {
//        List<StudentAddressResponse> response = studentAddressService.getAddOfStudent();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StudentAddressResponse> updateStudentAdd(
//            @PathVariable Long id,
//            @Valid @RequestBody UpdateStudentAddressRequest updateAddDto) {
//        StudentAddressResponse response =studentAddressService.updateStudentAdd(id, updateAddDto);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PatchMapping("/{id}")
//    public  ResponseEntity<StudentAddressResponse>patchUpdateStudentAdd(
//            @PathVariable Long id,
//            @Valid @RequestBody PatchUpdateStudentAddRequest patchUpdateStudentAddRequest  ){
//        StudentAddressResponse response=studentAddressService.patchUpdateStudent(id,patchUpdateStudentAddRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStudentAdd(
//            @PathVariable Long id) {
//        studentAddressService.deleteStudentAdd(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
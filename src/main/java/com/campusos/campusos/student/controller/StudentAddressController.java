
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
@RequestMapping("/api/students/{studentId}/addresses")
@RequiredArgsConstructor
public class StudentAddressController {

    @Autowired
    private final StudentAddressService studentAddressService;

    @PostMapping
    public ResponseEntity<StudentAddressResponse> createStudentAdd(
            @PathVariable Long studentId,
            @Valid @RequestBody CreateStudentAddressRequest createAddDto) {
        StudentAddressResponse response = studentAddressService.createStudentAdd(studentId,createAddDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{AddressId}")
    public ResponseEntity<StudentAddressResponse> getStudentAddById(
            @PathVariable Long studentId,
            @PathVariable Long AddressId) {
        StudentAddressResponse response = studentAddressService.getStudentAddById(studentId,AddressId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentAddressResponse>> getAddOfStudent(@PathVariable Long studentId) {
        List<StudentAddressResponse> response = studentAddressService.getAddOfStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{AddressId}")
    public ResponseEntity<StudentAddressResponse> updateStudentAdd(
            @PathVariable Long AddressId,
            @Valid @RequestBody UpdateStudentAddressRequest updateAddDto) {
        StudentAddressResponse response =studentAddressService.updateStudentAdd(AddressId, updateAddDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{AddressId}")
    public  ResponseEntity<StudentAddressResponse>patchUpdateStudentAdd(
            @PathVariable Long AddressId,
            @Valid @RequestBody PatchUpdateStudentAddRequest patchUpdateStudentAddRequest  ){
        StudentAddressResponse response=studentAddressService.patchUpdateStudent(AddressId,patchUpdateStudentAddRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{AddressId}")
    public ResponseEntity<Void> deleteStudentAdd(
            @PathVariable Long AddressId) {
        studentAddressService.deleteStudentAdd(AddressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
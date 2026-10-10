package com.campusos.campusos.student.controller;

import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.service.StudentDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/documents")
@RequiredArgsConstructor
public class StudentDocumentController {

    @Autowired
    private final StudentDocumentService studentDocumentService;

    @PostMapping
    public ResponseEntity<StudentDocumentResponse> createStudentDocument(
            @PathVariable Long studentId,
            @Valid @RequestBody CreateStudentDocumentRequest createDocDto) {
        StudentDocumentResponse response = studentDocumentService.createStudentDoc(studentId,createDocDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{docId}")
    public ResponseEntity<StudentDocumentResponse> getStudentDocById(
            @PathVariable Long studentId,
            @PathVariable Long docId) {
        StudentDocumentResponse response = studentDocumentService.getStudentDocById(studentId,docId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentDocumentResponse>> getAllStudentDocs(@PathVariable Long studentId) {
        List<StudentDocumentResponse> response = studentDocumentService.getAllStudentDocs(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{docId}")
    public ResponseEntity<StudentDocumentResponse> updateStudentDoc(
            @PathVariable Long studentId,
            @PathVariable Long docId,
            @Valid @RequestBody UpdateStudentDocumentRequest updateDocDto) {
        StudentDocumentResponse response =studentDocumentService.updateStudentDoc(studentId,docId, updateDocDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{docId}")
    public  ResponseEntity<StudentDocumentResponse>patchUpdateStudentDoc(
            @PathVariable Long studentId,
            @PathVariable Long docId,
            @Valid @RequestBody PatchUpdateStudentDocReq patchUpdateDocDto  ){
        StudentDocumentResponse response=studentDocumentService.patchUpdateStudentDoc(studentId,docId,patchUpdateDocDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<Void> deleteStudentDoc(
            @PathVariable Long studentId,
            @PathVariable Long docId) {
        studentDocumentService.deleteStudentDoc(studentId,docId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
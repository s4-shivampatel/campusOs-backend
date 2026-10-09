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
@RequestMapping("/api/students/{id}/documents")
@RequiredArgsConstructor
public class StudentDocumentController {

    @Autowired
    private final StudentDocumentService studentDocumentService;

    @PostMapping
    public ResponseEntity<StudentDocumentResponse> createStudentDocument(
            @PathVariable Long id,
            @Valid @RequestBody CreateStudentDocumentRequest createDocDto) {
        StudentDocumentResponse response = studentDocumentService.createStudentDoc(id,createDocDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{docId}")
    public ResponseEntity<StudentDocumentResponse> getStudentDocById(
            @PathVariable Long docId) {
        StudentDocumentResponse response = studentDocumentService.getStudentDocById(docId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentDocumentResponse>> getAllStudentDocs() {
        List<StudentDocumentResponse> response = studentDocumentService.getAllStudentDocs();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{docId}")
    public ResponseEntity<StudentDocumentResponse> updateStudentDoc(
            @PathVariable Long docId,
            @Valid @RequestBody UpdateStudentDocumentRequest updateDocDto) {
        StudentDocumentResponse response =studentDocumentService.updateStudentDoc(docId, updateDocDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{docId}")
    public  ResponseEntity<StudentDocumentResponse>patchUpdateStudentDoc(
            @PathVariable Long docId,
            @Valid @RequestBody PatchUpdateStudentDocReq patchUpdateDocDto  ){
        StudentDocumentResponse response=studentDocumentService.patchUpdateStudentDoc(docId,patchUpdateDocDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<Void> deleteStudentDoc(
            @PathVariable Long docId) {
        studentDocumentService.deleteStudentDoc(docId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
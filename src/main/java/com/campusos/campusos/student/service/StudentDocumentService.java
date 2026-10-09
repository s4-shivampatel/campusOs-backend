package com.campusos.campusos.student.service;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.CreateStudentDocumentRequest;
import com.campusos.campusos.student.dto.PatchUpdateStudentDocReq;
import com.campusos.campusos.student.dto.StudentDocumentResponse;
import com.campusos.campusos.student.dto.UpdateStudentDocumentRequest;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.entity.StudentDocument;
import com.campusos.campusos.student.repository.StudentDocumentRepository;
import com.campusos.campusos.student.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentDocumentService {

    private final StudentDocumentRepository studentDocumentRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    public StudentDocumentResponse createStudentDoc(Long id,@Valid CreateStudentDocumentRequest createDocDto) {
        Student student=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id "+id)
        );
        StudentDocument studentDocument= modelMapper.map(createDocDto,StudentDocument.class);
        studentDocument.setStudent(student);

        StudentDocument saveDocument=studentDocumentRepository.save(studentDocument);
        StudentDocumentResponse response=modelMapper.map(saveDocument,StudentDocumentResponse.class);
        response.setStudentId(saveDocument.getStudent().getId());
        return response;
    }

    public StudentDocumentResponse getStudentDocById(Long docId) {
        StudentDocument documentFounded=studentDocumentRepository.findById(docId).orElseThrow(
                ()->new ResourceNotFoundException("Document Not found with id:"+docId)
        );
        return modelMapper.map(documentFounded,StudentDocumentResponse.class);
    }

    public List<StudentDocumentResponse> getAllStudentDocs() {
        List<StudentDocument> allDocuments=studentDocumentRepository.findAll();
        List<StudentDocumentResponse> responses=new ArrayList<>();
        for (StudentDocument studentDocument:allDocuments){
            responses.add(modelMapper.map(studentDocument,StudentDocumentResponse.class));
        }
        return responses;
    }

    public StudentDocumentResponse updateStudentDoc(Long docId, @Valid UpdateStudentDocumentRequest updateDocDto) {
        StudentDocument documentFounded=studentDocumentRepository.findById(docId).orElseThrow(
                ()->new ResourceNotFoundException("Document Not found with id:"+docId)
        );
        modelMapper.map(updateDocDto,documentFounded);
        StudentDocument updatedDocument=studentDocumentRepository.save(documentFounded);
        return modelMapper.map(updatedDocument,StudentDocumentResponse.class);

    }

    public StudentDocumentResponse patchUpdateStudentDoc(Long docId, @Valid PatchUpdateStudentDocReq patchUpdateDocDto) {
        StudentDocument documentFounded=studentDocumentRepository.findById(docId).orElseThrow(
                ()->new ResourceNotFoundException("Document Not found with id:"+docId)
        );
        modelMapper.map(patchUpdateDocDto,documentFounded);
        StudentDocument documentUpdated=studentDocumentRepository.save(documentFounded);
        return modelMapper.map(documentUpdated,StudentDocumentResponse.class);
    }

    public void deleteStudentDoc(Long docId) {
        StudentDocument documentFounded=studentDocumentRepository.findById(docId).orElseThrow(
                ()->new ResourceNotFoundException("Document Not found with id:"+docId)
        );
        studentDocumentRepository.delete(documentFounded);
    }
}

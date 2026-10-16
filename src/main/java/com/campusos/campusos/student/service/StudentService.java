package com.campusos.campusos.student.service;


import com.campusos.campusos.common.exception.DuplicateResourceException;
import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.*;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import com.campusos.campusos.user.entity.User;
import com.campusos.campusos.user.repository.UserRepository;

import com.campusos.campusos.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.campusos.campusos.common.enums.StudentStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class StudentService {

    //@Autowired not required due to @RequiredArgsConstructor
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserService userService;


//    public StudentResponse createStudent(@Valid CreateStudentRequest createDto) {
//        if(studentRepository.existsByEmail(createDto.getEmail())){
//            throw new DuplicateResourceException("email '"+createDto.getEmail()+"' already exist");
//        }
//        if(studentRepository.existsByRollNumber(createDto.getRollNumber())){
//            throw new DuplicateResourceException("Student with roll number '"+createDto.getRollNumber()+"' already exists");
//        }
//        if(userRepository.existsByUsername(createDto.getUsername())){
//            throw new DuplicateResourceException("Username  '"+createDto.getEmail()+"' already taken");
//        }
//        if (userRepository.existsByEmail(createDto.getEmail())) {
//            throw new DuplicateResourceException("User email '" + createDto.getEmail() + "' already exists");
//        }
//        //create student
//        Student student=modelMapper.map(createDto, Student.class);
//
//        //<------Creating User------->
//        User user=userService.createStudentUser(createDto);
//
//        //jo values model mapper ki help se set nhi hogi
//        if(student.getStatus()==null){
//            student.setStatus(ACTIVE);
//        }
//        student.setUser(user);
//        Student savedStudent=studentRepository.save(student);
//        return modelMapper.map(savedStudent,StudentResponse.class);
//
//    }
    public StudentResponse getStudentById(Long id) {

        Student  savedStudent=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id: " + id)
        );
        StudentResponse response=modelMapper.map(savedStudent,StudentResponse.class);
        response.getAddresses().forEach((StudentAddressResponse add)->add.setStudentId(id));
        response.getDocuments().forEach((StudentDocumentResponse doc)->doc.setStudentId(id));
        response.getEnrollments().forEach((StudentEnrollmentResponse enroll)->enroll.setStudentId(id));
        response.getGuardians().forEach((StudentGuardianResponse guardian)->guardian.setStudentId(id));
        if (response.getStudentProfile()!=null){
            response.getStudentProfile().setStudentId(id);
        }

        return response ;

    }

    public List<StudentResponse> getAllStudents() {
        List<Student> savedStudents=studentRepository.findAll();
        List<StudentResponse> response=new ArrayList<>();
        for(Student student:savedStudents){
            response.add(modelMapper.map(student,StudentResponse.class));
        }
        return response;
    }

    public StudentResponse updateStudent(Long id, @Valid UpdateStudentRequest updateDto) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        modelMapper.map(updateDto,studentFounded);
        Student updatedStudent=studentRepository.save(studentFounded);
        return modelMapper.map(updatedStudent,StudentResponse.class);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        User user=userRepository.findById(studentFounded.getUser().getId()).orElseThrow(
                ()->new ResourceNotFoundException("User not found")
        );
        studentRepository.delete(studentFounded);
        userRepository.delete(user);
    }


    public StudentResponse patchUpdateStudent(Long id, @Valid PatchUpdateStudentRequest patchUpdateStudentRequest) {
        Student studentFounded=studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("student not found with id:"+id)
        );
        modelMapper.map(patchUpdateStudentRequest,studentFounded);

        Student savedStudent=studentRepository.save(studentFounded);
        return modelMapper.map(savedStudent,StudentResponse.class);

    }
}

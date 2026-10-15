package com.campusos.campusos.auth.service;


import com.campusos.campusos.common.exception.DuplicateResourceException;
import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import com.campusos.campusos.user.dto.UserStudentResponseDto;
import com.campusos.campusos.user.entity.Role;
import com.campusos.campusos.user.entity.User;
import com.campusos.campusos.user.entity.UserRole;
import com.campusos.campusos.user.repository.RoleRepository;
import com.campusos.campusos.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public UserStudentResponseDto createStudentUser(@Valid CreateStudentRequest createStudentRequest) {

        //<-----------Duplicate Validation--------->
        //In Student Entity
        if(studentRepository.existsByEmail(createStudentRequest.getEmail())){
            throw new DuplicateResourceException("email '"+createStudentRequest.getEmail()+"' already exist");
        }
        if(studentRepository.existsByRollNumber(createStudentRequest.getRollNumber())){
            throw new DuplicateResourceException("Student with roll number '"+createStudentRequest.getRollNumber()+"' already exists");
        }
        //In User Entity
        if(userRepository.existsByUsername(createStudentRequest.getUsername())){
            throw new DuplicateResourceException("Username  '"+createStudentRequest.getEmail()+"' already taken");
        }
        if(userRepository.existsByUsername(createStudentRequest.getUsername())){
            throw new DuplicateResourceException("Username  '"+createStudentRequest.getEmail()+"' already taken");
        }

        //<-----------User Creating--------->
        User user=modelMapper.map(createStudentRequest,User.class);
        user.setPassword(passwordEncoder.encode(createStudentRequest.getPassword()));
        //Role assigning
        Role studentRole = roleRepository.findByName("STUDENT").orElseThrow(
                () -> new ResourceNotFoundException("STUDENT role not found")
        );
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(studentRole);
        user.getUserRole().add(userRole);


        //<-----------Student Creation--------->
        Student student=modelMapper.map(createStudentRequest, Student.class);


        //<-----------Setting Both Side Relations--------->
        student.setUser(user);
        user.setStudent(student);


        //<-----------Saving User and Student--------->
        User savedUser=userRepository.save(user);
        studentRepository.save(student);

        return modelMapper.map(savedUser, UserStudentResponseDto.class);
    }
}

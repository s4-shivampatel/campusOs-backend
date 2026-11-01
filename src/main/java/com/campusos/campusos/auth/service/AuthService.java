package com.campusos.campusos.auth.service;


import com.campusos.campusos.auth.dto.StudentLoginRequestDto;
import com.campusos.campusos.auth.dto.StudentLoginResponseDto;
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
import com.campusos.campusos.user.repository.UserRoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public UserStudentResponseDto createStudentUser(@Valid CreateStudentRequest createStudentRequest) {

        //<-----------Duplicate Validation--------->
        //In Student Entity
        if (studentRepository.existsByEmail(createStudentRequest.getEmail())) {
            throw new DuplicateResourceException("email '" + createStudentRequest.getEmail() + "' already exist");
        }
        if (studentRepository.existsByRollNumber(createStudentRequest.getRollNumber())) {
            throw new DuplicateResourceException("Student with roll number '" + createStudentRequest.getRollNumber() + "' already exists");
        }
        //In User Entity
        if (userRepository.existsByUsername(createStudentRequest.getUsername())) {
            throw new DuplicateResourceException("Username  '" + createStudentRequest.getEmail() + "' already taken");
        }
        if (userRepository.existsByUsername(createStudentRequest.getUsername())) {
            throw new DuplicateResourceException("Username  '" + createStudentRequest.getEmail() + "' already taken");
        }

        //<-----------User Creating--------->
        User user = modelMapper.map(createStudentRequest, User.class);
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
        Student student = modelMapper.map(createStudentRequest, Student.class);


        //<-----------Setting Both Side Relations--------->
        student.setUser(user);
        user.setStudent(student);


        //<-----------Saving User and Student--------->
        User savedUser = userRepository.save(user);
        studentRepository.save(student);
        userRoleRepository.save(userRole);


        UserStudentResponseDto userStudentResponseDto = modelMapper.map(savedUser, UserStudentResponseDto.class);
        userStudentResponseDto.setStudentId(student.getId());
        List<String> roles = user.getUserRole()
                .stream()
                .map(role -> role.getRole().getName())
                .toList();
        userStudentResponseDto.setUserRole(roles);

        return userStudentResponseDto;
    }

    public StudentLoginResponseDto studentLogin(@Valid StudentLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with username:" + request.getUsername())
        );
        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );
        if (!passwordMatches) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password"
            );
        }
        StudentLoginResponseDto response=modelMapper.map(request,StudentLoginResponseDto.class);
        response.setId(user.getId());
        response.setMessage("Login Successful");
        return response;
    }
}




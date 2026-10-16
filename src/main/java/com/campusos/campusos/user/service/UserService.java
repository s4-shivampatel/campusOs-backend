package com.campusos.campusos.user.service;


import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.dto.StudentResponse;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import com.campusos.campusos.user.dto.UserStudentResponseDto;
import com.campusos.campusos.user.entity.Role;
import com.campusos.campusos.user.entity.User;

import com.campusos.campusos.user.entity.UserRole;
import com.campusos.campusos.user.repository.RolesRepository;
import com.campusos.campusos.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.campusos.campusos.common.enums.UserStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;



//    public User createStudentUser(@Valid CreateStudentRequest createDto) {
//        User user=new User();
//        user.setUsername(createDto.getUsername());
//        user.setEmail(createDto.getEmail());
//        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
//        if (user.getStatus()==null){
//            user.setStatus(ACTIVE);
//        }
//        return userRepository.save(user);
//    }


    //              <--------Getting All Users---------->
    public List<UserStudentResponseDto> getAllUsers() {
        List<User> AllUsers=userRepository.findAll();
        List<UserStudentResponseDto> allUsersResponse=new ArrayList<>();
        for(User user:AllUsers){
            allUsersResponse.add(modelMapper.map(user,UserStudentResponseDto.class));
        }
        return allUsersResponse;
    }
    //              <--------Getting All Users By userId---------->
    public UserStudentResponseDto getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id:"+userId)
        );
        return modelMapper.map(user,UserStudentResponseDto.class);
    }

    //              <--------Deleting Users By userId---------->
    @Transactional
    public String deleteUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id:"+userId)
        );
        Student student=studentRepository.findById(user.getStudent().getId()).orElseThrow(
                ()->new ResourceNotFoundException("student not found")
        );
        userRepository.deleteById(userId);
        studentRepository.delete(student);
        return "User deleted successfully";

    }
}

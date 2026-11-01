package com.campusos.campusos.user.service;


import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.student.entity.Student;
import com.campusos.campusos.student.repository.StudentRepository;
import com.campusos.campusos.user.dto.UserStudentResponseDto;
import com.campusos.campusos.user.entity.User;
import com.campusos.campusos.user.repository.RoleRepository;
import com.campusos.campusos.user.repository.UserRepository;
import com.campusos.campusos.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

//      <------user creation ka kaam auth service me ho rha h------>
//    @Transactional
//    public User createStudentUser(@Valid CreateStudentRequest createDto) {
//        User user=new User();
//        user.setUsername(createDto.getUsername());
//        user.setEmail(createDto.getEmail());
//        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
//        if (user.getStatus()==null){
//            user.setStatus(ACTIVE);
//        }
//        User savedUser=userRepository.save(user);
//
//        UserRole userRole=new UserRole();
//        userRole.setUser(savedUser);
//        Role role=roleRepository.findByName("STUDENT").orElseThrow(
//                ()->new ResourceNotFoundException("Role Not found")
//        );
//        userRole.setRole(role);
//        userRoleRepository.save(userRole);
//
//        return savedUser;
//    }


    //              <--------Getting All Users---------->
    public List<UserStudentResponseDto> getAllUsers() {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        if (authentication!=null){
//            String username= authentication.getName();
//            User loggedInUser=userRepository.findByEmail(username).orElseThrow(
//                    ()-> new ResourceNotFoundException("User not found with username:"+username)
//            );
//            if (loggedInUser.getUserRole())
//        }
        List<User> AllUsers=userRepository.findAll();

        List<UserStudentResponseDto> allUsersResponse=new ArrayList<>();
        for(User user:AllUsers){
            UserStudentResponseDto res=modelMapper.map(user,UserStudentResponseDto.class);
            if (user.getStudent() != null) {
                res.setStudentId(user.getStudent().getId());
            }
            List<String> roles = user.getUserRole()
                    .stream()
                    .map(userRole -> userRole.getRole().getName())
                    .toList();

            res.setUserRole(roles);
            allUsersResponse.add(res);
        }
        return allUsersResponse;
    }
    //              <--------Getting All Users By userId---------->
    public UserStudentResponseDto getUserById(Long userId) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            String username=authentication.getName();
            User loggedInUser=userRepository.findByEmail(username).orElseThrow(
                    ()-> new ResourceNotFoundException("User not found with username:"+username)
            );
            if (!loggedInUser.getId().equals(userId)){
                throw new AccessDeniedException("You cannot access another user's data");
            };
        }

        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id:"+userId)
        );
        UserStudentResponseDto responseDto=modelMapper.map(user,UserStudentResponseDto.class);
        if (user.getStudent()!=null){
            responseDto.setStudentId(user.getStudent().getId());
        }
        List<String> roles = user.getUserRole()
                .stream()
                .map(userRole -> userRole.getRole().getName())
                .toList();
        responseDto.setUserRole(roles);
        return responseDto;
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

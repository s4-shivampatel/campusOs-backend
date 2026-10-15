package com.campusos.campusos.user.service;


import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.user.entity.Role;
import com.campusos.campusos.user.entity.User;

import com.campusos.campusos.user.entity.UserRole;
import com.campusos.campusos.user.repository.RolesRepository;
import com.campusos.campusos.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.campusos.campusos.common.enums.UserStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    public User createStudentUser(@Valid CreateStudentRequest createDto) {
        User user=new User();
        user.setUsername(createDto.getUsername());
        user.setEmail(createDto.getEmail());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        if (user.getStatus()==null){
            user.setStatus(ACTIVE);
        }
        return userRepository.save(user);
    }
}

package com.campusos.campusos.auth.controller;

import com.campusos.campusos.auth.service.AuthService;
import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.user.dto.UserStudentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/students-register")
    public ResponseEntity<UserStudentResponseDto> createStudentUser(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        UserStudentResponseDto user=authService.createStudentUser(createStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

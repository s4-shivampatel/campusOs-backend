package com.campusos.campusos.user.controller;

import com.campusos.campusos.user.dto.UserStudentResponseDto;
import com.campusos.campusos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //              <--------Getting All Users---------->
    @GetMapping
    public ResponseEntity<List<UserStudentResponseDto>> getAllUsers(){
        List<UserStudentResponseDto> users=userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    //              <--------Getting All Users By userId---------->
    @GetMapping("/{userId}")
    public ResponseEntity<UserStudentResponseDto> getUserById(
            @PathVariable Long userId
    ){
        UserStudentResponseDto user=userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{userId}")
    public  ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        String msg=userService.deleteUserById(userId);
        return ResponseEntity.ok(msg);
    }




}

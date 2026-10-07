package com.campusos.campusos.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDto {

    @Size(min = 3, max = 50)
    private String username;

    @Email
    private String email;

    private Boolean enabled;
}
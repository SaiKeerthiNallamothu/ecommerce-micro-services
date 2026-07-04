package com.stschool.springsecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class LoginRequestDto {
    @NotBlank(message="Please give correct email")
    @Email(message ="Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    @Size(min=8,message = "Password must contains at least 8 characters")
    private String password;
}

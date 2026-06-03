package com.stschool.springsecurity.dto;

import com.stschool.springsecurity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class SignupRequestDto {
    @NotBlank(message = "please give correct name")
    @Pattern(regexp = "^(?=.*[A-Z],[a-z]).{8,16}$",message = "Please  dont include numbers in name")
   private String name;
    @Email(message ="Invalid email")
   private String email;
    @NotBlank(message = "please give correct password")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.[$*&]).{8,16}$")
   private String password;
    @NotNull(message = "please select correct role")
   private Role role;
}

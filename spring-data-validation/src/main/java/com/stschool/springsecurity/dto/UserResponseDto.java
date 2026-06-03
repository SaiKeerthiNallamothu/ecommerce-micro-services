package com.stschool.springsecurity.dto;

import com.stschool.springsecurity.enums.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private Role role;
}

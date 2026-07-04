package com.stschool.springsecurity.dto;

import com.stschool.springsecurity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String password;
}

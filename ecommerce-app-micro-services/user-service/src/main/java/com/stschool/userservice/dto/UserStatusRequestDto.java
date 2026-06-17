package com.stschool.userservice.dto;


import com.stschool.userservice.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserStatusRequestDto {
    @NotBlank(message = "Status is required")
    private int id;
    @NotBlank(message = "Status is required")
    private Status status;
    @NotBlank(message = "Reason is required")
    private String reason;
}


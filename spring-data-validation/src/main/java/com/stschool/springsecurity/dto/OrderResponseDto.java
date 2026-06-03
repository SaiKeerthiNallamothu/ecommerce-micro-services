package com.stschool.springsecurity.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class OrderResponseDto {
    private Long id;

    private String orderNumber;

    private Double totalAmount;

    private LocalDateTime orderDate;
}

package com.stschool.springsecurity.dto;

import lombok.Data;

@Data

public class ProductResponseDto {
    Integer id;

    String name;

    double price;

    String category;

    boolean available;
}

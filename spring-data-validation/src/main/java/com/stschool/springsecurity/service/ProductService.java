package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.ProductRequestDto;
import com.stschool.springsecurity.dto.ProductResponseDto;
import com.stschool.springsecurity.exception.ProductExistsException;
import com.stschool.springsecurity.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDto save(ProductRequestDto productRequestDto) throws ProductExistsException;
    List<ProductResponseDto> getAll();
    ProductResponseDto findById(int id) throws ProductNotFoundException;
    ProductResponseDto update(int id, ProductRequestDto productRequestDto) throws ProductNotFoundException;
    void deleteById(int id) throws ProductNotFoundException;
}

package com.stschool.productservice.service;

import com.stschool.productservice.dto.request.ProductRequestDto;
import com.stschool.productservice.dto.request.ProductUpdateRequestDto;
import com.stschool.productservice.dto.response.CategoryCountDto;
import com.stschool.productservice.dto.response.CompanyCountDto;
import com.stschool.productservice.dto.response.ProductResponseDto;
import com.stschool.productservice.dto.response.StatusCountDto;
import com.stschool.productservice.enums.Category;
import com.stschool.productservice.enums.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto save(ProductRequestDto requestDto);

    ProductResponseDto getById(String productId);
    List<ProductResponseDto> getAll();
    ProductResponseDto update(
            String productId,
            ProductUpdateRequestDto productUpdateRequestDto
    );
    void delete(String productId);
    Page<ProductResponseDto> getAllByPage(
            int page,
            int size,
            String sortBy
    );

    List<ProductResponseDto> getByCategory(
            Category category
    );

    List<ProductResponseDto> getByStatus(
            Status status
    );

    List<ProductResponseDto> getTopRatedProducts();


    List<ProductResponseDto> searchProducts(
            String keyword
    );

    List<CategoryCountDto> countProductsByCategory();

    List<CompanyCountDto> countProductsByCompany();

    List<StatusCountDto> countProductsByStatus();
}

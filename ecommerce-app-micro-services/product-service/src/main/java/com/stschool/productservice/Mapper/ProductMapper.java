package com.stschool.productservice.Mapper;


import com.stschool.productservice.dto.response.ProductResponseDto;
import com.stschool.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductResponseDto mapToResponseDto(
            Product product) {

        ProductResponseDto responseDto =
                modelMapper.map(
                        product,
                        ProductResponseDto.class
                );

        int sellingPrice =
                product.getMaxRetailPrice()
                        - (
                        product.getMaxRetailPrice()
                                * product.getDiscountPercentage()
                                / 100
                );

        responseDto.setSellingPrice(sellingPrice);

        return responseDto;
    }
}
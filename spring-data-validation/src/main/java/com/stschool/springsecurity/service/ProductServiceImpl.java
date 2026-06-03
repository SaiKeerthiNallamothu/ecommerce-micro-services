package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.ProductRequestDto;
import com.stschool.springsecurity.dto.ProductResponseDto;
import com.stschool.springsecurity.entity.Product;
import com.stschool.springsecurity.exception.ProductExistsException;
import com.stschool.springsecurity.exception.ProductNotFoundException;
import com.stschool.springsecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j

public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) throws ProductExistsException {
        log.info("{} saving product {}",getClass().getSimpleName(),productRequestDto);
        if(productRepository.existsByName(productRequestDto.getName())){
            throw new ProductExistsException(" Product already exists with name:" + productRequestDto.getName());
        }
    Product product= modelMapper.map(productRequestDto,Product.class);
       Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct,ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product,ProductResponseDto.class))
                .toList();
    }

    @Override
    public ProductResponseDto findById(int id) throws ProductNotFoundException {
       Product product = productRepository.findById(id).
                orElseThrow(()->new ProductNotFoundException
                        ("Product not found with id : " + id));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto update(int id, ProductRequestDto productRequestDto) throws ProductNotFoundException {
       Product existingProduct= productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException
                        ("Product not found with id : " + id));
       existingProduct.setPrice(productRequestDto.getPrice());
       existingProduct.setAvailable(productRequestDto.isAvailable());
        Product updateProduct = productRepository.save(existingProduct);

        return modelMapper.map(updateProduct,ProductResponseDto.class);
    }

    @Override
    public void deleteById(int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).
                orElseThrow(()->new ProductNotFoundException
                        ("Product not found with id : " + id));
        productRepository.deleteById(id);

    }
}

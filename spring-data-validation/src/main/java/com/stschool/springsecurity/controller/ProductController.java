package com.stschool.springsecurity.controller;

import com.stschool.springsecurity.dto.ProductRequestDto;
import com.stschool.springsecurity.dto.ProductResponseDto;
import com.stschool.springsecurity.exception.ProductExistsException;
import com.stschool.springsecurity.exception.ProductNotFoundException;
import com.stschool.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor

public class ProductController {
    private  final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto productRequestDto) throws ProductExistsException{
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRequestDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>>getAll(){

        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable int id)throws ProductNotFoundException{
        return ResponseEntity.ok(productService.findById(id));
    }
    //update
    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable int id,@RequestBody ProductRequestDto productRequestDto)throws ProductNotFoundException{
        return ResponseEntity.ok((productService.update(id,productRequestDto)));
    }
    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable int id) throws ProductNotFoundException{
        productService.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}

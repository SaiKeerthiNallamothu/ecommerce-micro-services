package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.OrderRequestDto;
import com.stschool.springsecurity.dto.OrderResponseDto;
import com.stschool.springsecurity.entity.Order;
import com.stschool.springsecurity.exception.OrderAlreadyExistsException;
import com.stschool.springsecurity.exception.OrderNotFoundException;
import com.stschool.springsecurity.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    OrderResponseDto save(OrderRequestDto orderRequestDto) throws OrderAlreadyExistsException;

    List<Order> getAll();

    OrderResponseDto findByOrderNumber(String orderNumber)throws OrderNotFoundException;

    OrderResponseDto update( String orderNumber,OrderRequestDto orderRequestDto) throws OrderNotFoundException;

    void delete(String orderNumber)throws OrderNotFoundException;
}

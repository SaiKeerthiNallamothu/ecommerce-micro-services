package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.OrderRequestDto;
import com.stschool.springsecurity.dto.OrderResponseDto;
import com.stschool.springsecurity.dto.ProductResponseDto;
import com.stschool.springsecurity.entity.Order;
import com.stschool.springsecurity.entity.Product;
import com.stschool.springsecurity.exception.OrderAlreadyExistsException;
import com.stschool.springsecurity.exception.OrderNotFoundException;
import com.stschool.springsecurity.exception.ProductNotFoundException;
import com.stschool.springsecurity.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto) throws OrderAlreadyExistsException {
        log.info("{} savedOrder {}",getClass().getSimpleName(),orderRequestDto);
        if(orderRepository.existsByOrderNumber(orderRequestDto.getOrderNumber())){
            throw new OrderAlreadyExistsException("Order already exists with number: "
                            + orderRequestDto.getOrderNumber());
        }
        Order order = modelMapper.map(orderRequestDto,Order.class);
        Order saved = orderRepository.save(order);
        return modelMapper.map(saved, OrderResponseDto.class);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order,Order.class))
                .toList();
    }

    @Override
    public OrderResponseDto findByOrderNumber(String orderNumber) throws OrderNotFoundException {
        Order order = orderRepository.findByOrderNumber(orderNumber).
                orElseThrow(()->new OrderNotFoundException
                        ("Product not found with id : " + orderNumber));
        return modelMapper.map(order, OrderResponseDto.class);

    }

    @Override
    public OrderResponseDto update(String orderNumber,OrderRequestDto orderRequestDto) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findByOrderNumber(orderNumber).
                orElseThrow(()->new OrderNotFoundException
                        ("Product not found with id : " + orderNumber));
        existingOrder.setTotalAmount(orderRequestDto.getTotalAmount());
        existingOrder.setOrderDate(LocalDateTime.now());
        return null;
    }

    @Override
    public void delete(String orderNumber) throws OrderNotFoundException {
        Order order = orderRepository.findByOrderNumber(orderNumber).
                orElseThrow(()->new OrderNotFoundException
                        ("Product not found with id : " + orderNumber));
        orderRepository.delete(order);

    }
}

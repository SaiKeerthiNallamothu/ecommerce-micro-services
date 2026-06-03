package com.stschool.springsecurity.controller;

import com.stschool.springsecurity.dto.OrderRequestDto;
import com.stschool.springsecurity.dto.OrderResponseDto;
import com.stschool.springsecurity.entity.Order;
import com.stschool.springsecurity.exception.OrderAlreadyExistsException;
import com.stschool.springsecurity.exception.OrderNotFoundException;
import com.stschool.springsecurity.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor


public class OrderController {
    private final OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<OrderResponseDto> save(OrderRequestDto orderRequestDto) throws OrderAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderRequestDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> findByOrderNumber(@PathVariable String orderNumber)throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.findByOrderNumber(orderNumber));
    }
    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable String orderNumber, @RequestBody OrderRequestDto orderRequestDto) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.update(orderNumber,orderRequestDto));
    }
    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<?>deleteOrder(@PathVariable String orderNumber) throws OrderNotFoundException {
        orderService.delete(orderNumber);

        return ResponseEntity.ok("Order deleted successfully");
    }


}

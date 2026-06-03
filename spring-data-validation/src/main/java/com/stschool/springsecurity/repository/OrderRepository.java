package com.stschool.springsecurity.repository;

import com.stschool.springsecurity.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);
}

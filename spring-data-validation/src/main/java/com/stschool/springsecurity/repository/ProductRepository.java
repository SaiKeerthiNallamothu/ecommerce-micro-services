package com.stschool.springsecurity.repository;

import com.stschool.springsecurity.entity.Product;
import com.stschool.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);


    Optional<Product> findByName(String name);
}

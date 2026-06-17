package com.stschool.spring.repository;

import com.stschool.spring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class ProductRepository {
    private final List<Product> products;


    public ProductRepository()  {
        products = new ArrayList<>();
    }

    public Product save(Product product) {
        this.products.add(product);
        return product;
    }

    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public List<Product> findAll(){
        return this.products;
    }

    public Product update(int id, Product product) {
        products.replaceAll(p -> p.getId() == id ? product : p);
        return product;
    }

    public boolean delete(int id) {
        return products.removeIf(product -> product.getId() == id);
    }

    public boolean delete(Product product){
        return products.remove(product);
    }
}

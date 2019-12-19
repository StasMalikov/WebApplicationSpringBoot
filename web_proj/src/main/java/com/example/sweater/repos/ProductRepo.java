package com.example.sweater.repos;

import com.example.sweater.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
}

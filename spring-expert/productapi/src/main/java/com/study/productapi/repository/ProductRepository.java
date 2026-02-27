package com.study.productapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByName(String name);

}

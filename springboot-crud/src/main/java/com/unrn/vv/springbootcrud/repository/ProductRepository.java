package com.unrn.vv.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unrn.vv.springbootcrud.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}


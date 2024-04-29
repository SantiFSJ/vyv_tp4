package com.unrn.vv.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unrn.vv.crud.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}


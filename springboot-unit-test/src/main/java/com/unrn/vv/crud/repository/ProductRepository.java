package com.unrn.vv.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unrn.vv.crud.model.Product;
import com.unrn.vv.crud.service.MyException;


public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);

}


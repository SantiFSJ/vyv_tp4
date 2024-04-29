package com.unrn.vv.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unrn.vv.crud.entity.Product;

public interface TestH2Repository extends JpaRepository<Product,Integer> {
}

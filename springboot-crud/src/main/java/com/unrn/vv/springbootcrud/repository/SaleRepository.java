package com.unrn.vv.springbootcrud.repository;

import com.unrn.vv.springbootcrud.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Integer> {
    Sale findByName(String name);
}

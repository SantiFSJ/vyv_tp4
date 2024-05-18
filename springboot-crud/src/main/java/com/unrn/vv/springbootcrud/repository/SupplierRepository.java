package com.unrn.vv.springbootcrud.repository;

import com.unrn.vv.springbootcrud.model.Sale;
import com.unrn.vv.springbootcrud.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}


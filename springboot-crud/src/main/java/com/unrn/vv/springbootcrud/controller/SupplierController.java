package com.unrn.vv.springbootcrud.controller;


import com.unrn.vv.springbootcrud.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unrn.vv.springbootcrud.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        supplier = service.saveSupplier(supplier);
        return supplier;
    }


    @GetMapping
    public List<Supplier> findAllSuppliers() {
        return service.getSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier findSupplierById(@PathVariable int id) {
        return service.getSupplierById(id);
    }


    @PutMapping("/update/{id}")
    public Supplier updateSupplier(@RequestBody Supplier supplier, @PathVariable int id) {
        return service.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable int id) {
        return service.deleteSupplier(id);
    }
}

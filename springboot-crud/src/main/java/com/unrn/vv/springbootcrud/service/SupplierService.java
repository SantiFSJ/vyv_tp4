package com.unrn.vv.springbootcrud.service;

import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.model.Supplier;
import com.unrn.vv.springbootcrud.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public Supplier saveSupplier(Supplier supplier) {
        return this.repository.save(supplier);
    }

    public List<Supplier> getSuppliers() {
        return this.repository.findAll();
    }

    public Supplier getSupplierById(int id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Supplier updateSupplier(int id, Supplier supplier) {
        Supplier existingProduct = repository.findById(id).orElse(null);
        existingProduct.setName(supplier.getName());
        existingProduct.setState(supplier.getState());
        existingProduct.setCity(supplier.getCity());
        existingProduct.setPostalCode(supplier.getPostalCode());
        return repository.save(existingProduct);
    }

    public String deleteSupplier(int id) {
        this.repository.deleteById(id);
        return "ok";
    }
}

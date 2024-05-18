package com.unrn.vv.springbootcrud.service;

import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.model.Sale;
import com.unrn.vv.springbootcrud.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    public List<Sale> getSales() {
        return this.repository.findAll();

    }

    public Sale getSaleById(int id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Sale updateSale(int id, Sale sale) {
        Sale existingSale = repository.findById(id).orElse(null);
        existingSale.setState(sale.getState());
        existingSale.setProducts(sale.getProducts());
        existingSale.setTotal(sale.getTotal());
        return repository.save(existingSale);

    }

    public String deleteSale(int id) {
        return null;

    }

    public Sale saveSale(Sale sale) {
        return this.repository.save(sale);
    }
}

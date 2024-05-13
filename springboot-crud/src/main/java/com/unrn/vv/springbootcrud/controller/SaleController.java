package com.unrn.vv.springbootcrud.controller;


import com.unrn.vv.springbootcrud.model.Sale;
import com.unrn.vv.springbootcrud.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public Sale addSale(@RequestBody Sale sale) {
        sale = service.saveSale(sale);
        return sale;
    }


    @GetMapping
    public List<Sale> findAllProducts() {
        return service.getSales();
    }

    @GetMapping("/{id}")
    public Sale findSaleById(@PathVariable int id) {
        return service.getSaleById(id);
    }


    @PutMapping("/update/{id}")
    public Sale updateSale(@RequestBody Sale sale, @PathVariable int id) {
        return service.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteSale(id);
    }
}

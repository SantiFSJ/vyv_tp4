package com.unrn.vv.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unrn.vv.crud.model.Product;
import com.unrn.vv.crud.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() throws MyException {
        List<Product> productos;
        try {
            productos = repository.findAll();
        } catch (Exception e) {
            throw new MyException();
        }
        
        return productos;
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product deleted !! " + id;
    }
    public Product updateProduct(int productId,Product product) {
        //Product existingProduct = repository.findById(productId).orElse(null);
        Optional<Product> existingProduct = repository.findById(productId);
        existingProduct.ifPresent(
                p -> {
                    p.setName(product.getName());
                    p.setQuantity(product.getQuantity());
                    p.setPrice(product.getPrice());
                    repository.save(p);
                }

        );
        return existingProduct.orElse(null);
    }


}

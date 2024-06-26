package com.unrn.vv.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.unrn.vv.crud.repository.ProductRepository;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class SpringBootCrudApplication {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void init() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudApplication.class, args);
    }

}

package com.unrn.vv.springbootcrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    private LocalDate date;
    private double total;
    private String state;

    @ManyToMany
    @JoinTable(
            name = "sales_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private ArrayList<Product> products;

    public Sale(LocalDate date, double total, String state,ArrayList<Product> products) {
        this.date = date;
        this.total = total;
        this.state = state;
        this.products = products;
    }

}

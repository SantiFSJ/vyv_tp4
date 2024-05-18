package com.unrn.vv.springbootcrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    @Id
    @GeneratedValue
    private int id;
    private LocalDate date;
    private double total;
    private String state;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public Sale(LocalDate date, double total, String state,List<Product> products) {
        this.date = date;
        this.total = total;
        this.state = state;
        this.products = products;
    }

}

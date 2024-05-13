package com.unrn.vv.springbootcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity{
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String name;

    public Supplier(String street, String city, String state, String postalCode, String name) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.name = name;
    }

}

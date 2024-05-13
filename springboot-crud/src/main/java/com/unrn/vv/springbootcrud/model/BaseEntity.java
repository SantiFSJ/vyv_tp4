package com.unrn.vv.springbootcrud.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseEntity {
    @Id
    @GeneratedValue
    private int id;
}

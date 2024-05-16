package com.exercise.exercise.bean;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int calories;
    private double price;
}

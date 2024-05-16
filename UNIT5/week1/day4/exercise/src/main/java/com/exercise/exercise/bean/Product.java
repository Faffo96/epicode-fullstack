package com.exercise.exercise.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

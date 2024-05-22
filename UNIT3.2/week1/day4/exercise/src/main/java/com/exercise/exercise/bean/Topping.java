package com.exercise.exercise.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "toppings")
@ToString(callSuper = true)
public class Topping extends Product {
    private int quantity = 1;
    @ManyToMany(mappedBy = "toppings")
    private List<Pizza> pizzaList;

    @Override
    public String toString() {
        return "Topping{" +
                super.toString() +
                "quantity=" + quantity +
                '}';
    }
}

package com.exercise.exercise.bean;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "pizzas")
public class Pizza extends Product {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_toppings",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<Topping> toppings;

    @Override
    public String toString() {
        return "Pizza{" + super.toString() + "}";
    }
}

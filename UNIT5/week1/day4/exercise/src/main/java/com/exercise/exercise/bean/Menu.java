package com.exercise.exercise.bean;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private List<Pizza> pizzas;
    private List<Topping> toppings;
    private List<Drink> drinks;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizzas:\n");
        if (pizzas != null) {
            for (Pizza pizza : pizzas) {
                sb.append(pizza).append("\n");
            }
        }
        sb.append("Toppings:\n");
        if (toppings != null) {
            for (Topping topping : toppings) {
                sb.append(topping).append("\n");
            }
        }
        sb.append("Drinks:\n");
        if (drinks != null) {
            for (Drink drink : drinks) {
                sb.append(drink).append("\n");
            }
        }
        return sb.toString();
    }
}

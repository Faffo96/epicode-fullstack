package com.exercise.exercise.bean;

import lombok.Data;

@Data
public abstract class Product {
    private String Name;
    private int calories;
    private double price;
}

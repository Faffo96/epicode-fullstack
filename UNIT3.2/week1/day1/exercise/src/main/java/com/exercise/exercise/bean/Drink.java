package com.exercise.exercise.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Drink extends Product {
    private double liters;
    private int vol;
}

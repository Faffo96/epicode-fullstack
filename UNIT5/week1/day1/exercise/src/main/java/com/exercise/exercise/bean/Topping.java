package com.exercise.exercise.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Topping extends Product {
    private int quantity;
}

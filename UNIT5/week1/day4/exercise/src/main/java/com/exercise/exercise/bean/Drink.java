package com.exercise.exercise.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "drinks")
@ToString(callSuper = true)
public class Drink extends Product {
    private double liters;
    private int vol;
}

package com.exercise.exercise.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dining_table")
public class DiningTable {
    @Id
    @GeneratedValue
    private int tableId;
    private int maxSeatsNumber;
    private boolean busy;
    @OneToMany(mappedBy = "diningTable")
    private List<Order> orders;

    @Override
    public String toString() {
        return "DiningTable{" +
                "tableId=" + tableId +
                ", maxSeatsNumber=" + maxSeatsNumber +
                ", busy=" + busy +
                '}';
    }
}


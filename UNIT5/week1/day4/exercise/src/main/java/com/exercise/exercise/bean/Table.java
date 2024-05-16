package com.exercise.exercise.bean;

import lombok.Data;

@Data
public class Table {
    private int tableId;
    private int maxSeatsNumber;
    private boolean busy;
}

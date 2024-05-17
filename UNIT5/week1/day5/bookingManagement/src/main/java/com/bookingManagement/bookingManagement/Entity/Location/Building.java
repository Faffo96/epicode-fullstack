package com.bookingManagement.bookingManagement.Entity.Location;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue
    private int buildingId;
    private String name;
    private String address;
    private String city;
    @OneToMany(mappedBy = "building")
    private List<Office> offices;

    public Building(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
}

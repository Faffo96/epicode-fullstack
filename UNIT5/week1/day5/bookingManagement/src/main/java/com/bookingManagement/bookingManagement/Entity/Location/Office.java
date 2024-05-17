package com.bookingManagement.bookingManagement.Entity.Location;

import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue
    private int officeId;
    private String description;
    private OfficeType officeType;
    private int maxCapacity;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userReservation;

    public Office(String description, OfficeType officeType, int maxCapacity, Building building) {
        this.description = description;
        this.officeType = officeType;
        this.maxCapacity = maxCapacity;
        this.building = building;
    }
}

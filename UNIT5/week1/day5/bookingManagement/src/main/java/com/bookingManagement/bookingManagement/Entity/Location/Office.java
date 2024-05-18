package com.bookingManagement.bookingManagement.Entity.Location;

import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue
    private int officeId;
    private String description;
    @Enumerated (EnumType.STRING)
    private OfficeType officeType;
    private int maxCapacity;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToMany(mappedBy = "office")
    private List<Reservation> reservations;

    public Office(String description, OfficeType officeType, int maxCapacity, Building building) {
        this.description = description;
        this.officeType = officeType;
        this.maxCapacity = maxCapacity;
        this.building = building;
    }

    public Office() {
    }

    @Override
    public String toString() {
        return "\nOffice{" +
                "officeId=" + officeId +
                ", description='" + description + '\'' +
                ", officeType=" + officeType +
                ", maxCapacity=" + maxCapacity +
                ", building=" + building.getName() +
                "}";
    }
}

package com.bookingManagement.bookingManagement.Bean;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Catalogue {
    @Autowired
    private List<Building> buildings;
    @Autowired
    private List<Office> offices;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("------------------------------------------------\n\n" +
                "CATALOGUE:\n" +
                "\nBuildings:");
        if (buildings != null) {
            for (Building building : buildings) {
                sb.append(building);
            }
        }
        sb.append("\n\nOffices:");
        if (offices != null) {
            for (Office office : offices) {
                sb.append(office);
            }
        }
        sb.append("\n\n------------------------------------------------\n");
        return sb.toString();
    }
}

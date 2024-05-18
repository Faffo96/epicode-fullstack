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

        sb.append("------------------------------------------------ \n" +
                "CATALOGUE:\n" +
                "\nBuildings:\n");
        if (buildings != null) {
            for (Building building : buildings) {
                sb.append(building).append("\n");
            }
        }
        sb.append("\nOffices:\n");
        if (offices != null) {
            for (Office office : offices) {
                sb.append(office).append("\n");
            }
        }
        sb.append("------------------------------------------------ ");
        return sb.toString();
    }
}

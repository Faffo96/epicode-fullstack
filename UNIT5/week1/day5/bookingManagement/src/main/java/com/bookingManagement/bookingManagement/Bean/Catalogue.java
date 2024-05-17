package com.bookingManagement.bookingManagement.Bean;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class Catalogue {

    private List<Building> buildings;

    private List<Office> offices;
}

package com.bookingManagement.bookingManagement.appConfig;

import com.bookingManagement.bookingManagement.Bean.Catalogue;
import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import com.bookingManagement.bookingManagement.Service.BuildingService;
import com.bookingManagement.bookingManagement.Service.OfficeService;
import com.bookingManagement.bookingManagement.Service.ReservationService;
import com.bookingManagement.bookingManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Bean("Buildings")
    public List<Building> buildings() {
        return buildingService.getBuildings();
    }

    @Bean("Offices")
    public List<Office> offices(List<Building> buildingList) {
        return officeService.getOffices();
    }

    @Bean("Users")
    public List<User> users() {
        return userService.getUsers();
    }


    @Bean("Reservations")
    public List<Reservation> reservations() {
        return reservationService.getReservations();
    }


    @Bean("Catalogue")
    public Catalogue catalogue() {
        return new Catalogue();
    }
}

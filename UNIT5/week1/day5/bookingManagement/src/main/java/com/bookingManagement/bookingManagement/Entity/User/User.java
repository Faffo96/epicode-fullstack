package com.bookingManagement.bookingManagement.Entity.User;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int userId;
    String username;
    String name;
    String surname;
    String email;
    @OneToMany(mappedBy = "userReservation")
    List<Office> offices;

    public User(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}

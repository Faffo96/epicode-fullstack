package com.bookingManagement.bookingManagement.Entity.User;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Reservation;
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
    private String username;
    private String name;
    private String surname;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}

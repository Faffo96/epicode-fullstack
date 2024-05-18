package com.bookingManagement.bookingManagement.Entity;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "reservationDate", "office_id"}))
public class Reservation {
    @Id
    @GeneratedValue
    private int reservationId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
    private LocalDate reservationDate;
    private LocalDate expireDate;
    private int peopleQty;

    public Reservation(User user, Office office, LocalDate reservationDate, LocalDate expireDate, int peopleQty) {
        this.user = user;
        this.office = office;
        this.reservationDate = reservationDate;
        this.expireDate = expireDate;
        this.peopleQty = peopleQty;
    }

    public Reservation() {
    }
}

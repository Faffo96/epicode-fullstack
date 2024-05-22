package com.bookingManagement.bookingManagement.Repository;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.office.officeId = :officeId AND r.reservationDate = :reservationDate")
    Reservation findReservationsByOfficeAndDate(@Param("officeId") int officeId, @Param("reservationDate") LocalDate reservationDate);

    Reservation findByUserAndOfficeAndReservationDate(User user, Office office, LocalDate reservationDate);

    List<Reservation> findByOfficeAndReservationDate(Office office, LocalDate reservationDate);

    List<Reservation> findByUserAndReservationDate(User user, LocalDate reservationDate);
}


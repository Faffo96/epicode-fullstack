package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public void postReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public Reservation getReservation(int id){
        return reservationRepository.findById(id).get();
    }

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    public Reservation findReservationsByOfficeAndDate(int officeId, LocalDate reservationDate) {
        return reservationRepository.findReservationsByOfficeAndDate(officeId, reservationDate);
    }

    public boolean isReservationAvailable(User user, Office office, LocalDate reservationDate) {
        return reservationRepository.findByUserAndOfficeAndReservationDate(user, office, reservationDate) == null;
    }


    public Reservation createReservation(User user, Office office, LocalDate reservationDate, LocalDate expireDate) {
        // Verifica se l'utente ha già una prenotazione per questa data
        List<Reservation> userReservations = reservationRepository.findByUserAndReservationDate(user, reservationDate);
        if (!userReservations.isEmpty()) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data. Riprova.");
        }

        // Verifica se c'è già una prenotazione per questo ufficio e questa data
        List<Reservation> existingReservations = reservationRepository.findByOfficeAndReservationDate(office, reservationDate);
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("Prenotazione già esistente per questo ufficio e questa data. Riprova.");
        }

        // Se non ci sono conflitti, crea la prenotazione
        Reservation reservation = new Reservation(user, office, reservationDate, expireDate);
        return reservationRepository.save(reservation);
    }

    public boolean isReservationExpired(Reservation reservation) {
        return reservation.getExpireDate().isBefore(LocalDate.now());
    }

}

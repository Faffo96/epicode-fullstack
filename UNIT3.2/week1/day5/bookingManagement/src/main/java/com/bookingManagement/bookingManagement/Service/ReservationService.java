package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Exception.BookingManagementException;
import com.bookingManagement.bookingManagement.Repository.OfficeRepository;
import com.bookingManagement.bookingManagement.Repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OfficeRepository officeRepository;
    static Logger logger = LoggerFactory.getLogger("logger1");

    public void postReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public Reservation getReservation(int id){
        return reservationRepository.findById(id).get();
    }

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    public Reservation findReservationsByOfficeAndDate(int officeId, LocalDate reservationDate) throws BookingManagementException {
        Optional<Office> optionalOffice = officeRepository.findById(officeId);
        if (optionalOffice.isEmpty()) {
            throw new BookingManagementException("Office with id " + officeId + " not found.");
        }

        Reservation reservation = reservationRepository.findReservationsByOfficeAndDate(officeId, reservationDate);
        if (reservation == null) {
            throw new BookingManagementException("No reservations found for the office with ID " + officeId + " on date " + reservationDate);
        }

        return reservation;
    }



    public boolean isReservationAvailable(User user, Office office, LocalDate reservationDate) {
        return reservationRepository.findByUserAndOfficeAndReservationDate(user, office, reservationDate) == null;
    }


    public void createReservation(User user, Office office, LocalDate reservationDate, LocalDate expireDate, int peopleQty) throws BookingManagementException {
        // Verifica se l'utente ha già una prenotazione per questa data
        List<Reservation> userReservations = reservationRepository.findByUserAndReservationDate(user, reservationDate);
        if (!userReservations.isEmpty()) {
            throw new BookingManagementException("The user " + user.getUsername() + " already has a reservation in " + reservationDate +  ". Try again.");
        }

        // Verifica se c'è già una prenotazione per questo ufficio e questa data
        List<Reservation> existingReservations = reservationRepository.findByOfficeAndReservationDate(office, reservationDate);
        if (!existingReservations.isEmpty()) {
            throw new BookingManagementException("Reservation already exists for this office and this date. Try again.");
        }

        // Se non ci sono conflitti, crea la prenotazione
        Reservation reservation = new Reservation(user, office, reservationDate, expireDate, peopleQty);
        reservationRepository.save(reservation);
        System.out.println("Reservation created successfully: " + reservation);
        logger.trace("Reservation saved: " + reservation + "\nBy: " + user);
    }

    public boolean isReservationExpired(Reservation reservation) {
        return reservation.getExpireDate().isBefore(LocalDate.now());
    }

    public boolean isPeopleQtyWithinCapacity(Office office, int peopleQty) {
        return peopleQty <= office.getMaxCapacity();
    }
}

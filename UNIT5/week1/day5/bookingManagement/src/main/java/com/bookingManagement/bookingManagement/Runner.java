package com.bookingManagement.bookingManagement;

import com.bookingManagement.bookingManagement.Bean.Catalogue;
import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.Menu;
import com.bookingManagement.bookingManagement.Entity.Reservation;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import com.bookingManagement.bookingManagement.Service.BuildingService;
import com.bookingManagement.bookingManagement.Service.OfficeService;
import com.bookingManagement.bookingManagement.Service.ReservationService;
import com.bookingManagement.bookingManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private Menu menu;

    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BookingManagementApplication.class);



        /*System.out.println(reservationService.findReservationsByOfficeAndDate(1, LocalDate.of(2024, 5, 18)));

        System.out.println(officeService.findOfficesByTypeAndCity(OfficeType.PRIVATE, "Verona"));*/

        /*List<Building> buildings = (List<Building>) ctx.getBean("Buildings");

        List<Office> offices = (List<Office>) ctx.getBean("Offices");

        List<User> users = (List<User>) ctx.getBean("Users");

        List<Reservation> reservations = ctx.getBean("Reservations", List.class);*/


        /*populateDatabase();*/
        menu.startMenu();


    }

    public void populateDatabase() {
        List<Building> buildingList = Arrays.asList(
                new Building("Office Building A", "Via Roma", "Milano"),
                new Building("Office Building B", "Viale Europa", "Torino"),
                new Building("Office Building C", "Corso Italia", "Napoli"),
                new Building("Office Building D", "Via Firenze", "Roma"),
                new Building("Office Building E", "Piazza della Repubblica", "Bologna"),
                new Building("Office Building F", "Via Milano", "Genova"),
                new Building("Office Building G", "Corso Venezia", "Verona"),
                new Building("Office Building H", "Viale della Libertà", "Palermo"),
                new Building("Office Building I", "Via Torino", "Firenze"),
                new Building("Office Building J", "Via Garibaldi", "Bari")
        );

        for (Building building : buildingList) {
            buildingService.postBuilding(building);
        }

        List<Office> officeList = Arrays.asList(
                new Office("Private office with a great view", OfficeType.PRIVATE, 5, buildingList.get(0)),
                new Office("Spacious open space", OfficeType.OPENSPACE, 15, buildingList.get(1)),
                new Office("Meeting room with projector", OfficeType.MEETINGROOM, 8, buildingList.get(2)),
                new Office("Private office with modern design", OfficeType.PRIVATE, 4, buildingList.get(3)),
                new Office("Open space with natural light", OfficeType.OPENSPACE, 20, buildingList.get(4)),
                new Office("Large meeting room", OfficeType.MEETINGROOM, 12, buildingList.get(5)),
                new Office("Cozy private office", OfficeType.PRIVATE, 3, buildingList.get(6)),
                new Office("Open space with flexible desks", OfficeType.OPENSPACE, 25, buildingList.get(7)),
                new Office("Small meeting room", OfficeType.MEETINGROOM, 6, buildingList.get(8)),
                new Office("Private office with balcony", OfficeType.PRIVATE, 5, buildingList.get(9)),
                new Office("Open space with standing desks", OfficeType.OPENSPACE, 18, buildingList.get(0)),
                new Office("Meeting room with video conferencing", OfficeType.MEETINGROOM, 10, buildingList.get(1)),
                new Office("Quiet private office", OfficeType.PRIVATE, 4, buildingList.get(2)),
                new Office("Open space near the window", OfficeType.OPENSPACE, 22, buildingList.get(3)),
                new Office("Medium meeting room", OfficeType.MEETINGROOM, 9, buildingList.get(4)),
                new Office("Private office with ensuite bathroom", OfficeType.PRIVATE, 6, buildingList.get(5)),
                new Office("Bright open space", OfficeType.OPENSPACE, 17, buildingList.get(6)),
                new Office("Meeting room with round table", OfficeType.MEETINGROOM, 8, buildingList.get(7)),
                new Office("Private office with built-in storage", OfficeType.PRIVATE, 5, buildingList.get(8)),
                new Office("Large open space with breakout area", OfficeType.OPENSPACE, 30, buildingList.get(9)),
                new Office("Meeting room with whiteboard", OfficeType.MEETINGROOM, 7, buildingList.get(0)),
                new Office("Executive private office", OfficeType.PRIVATE, 6, buildingList.get(1)),
                new Office("Collaborative open space", OfficeType.OPENSPACE, 20, buildingList.get(2)),
                new Office("Meeting room for workshops", OfficeType.MEETINGROOM, 10, buildingList.get(3)),
                new Office("Small private office", OfficeType.PRIVATE, 2, buildingList.get(4)),
                new Office("Open space with creative zones", OfficeType.OPENSPACE, 25, buildingList.get(5)),
                new Office("Private room with breakout area", OfficeType.PRIVATE, 9, buildingList.get(6)),
                new Office("Private office with a view", OfficeType.PRIVATE, 5, buildingList.get(7)),
                new Office("Open space with quiet zones", OfficeType.OPENSPACE, 18, buildingList.get(8)),
                new Office("Meeting room with flexible seating", OfficeType.MEETINGROOM, 8, buildingList.get(9))
        );


        for (Office office : officeList) {
            officeService.postOffice(office);
        }

        List<User> userList = Arrays.asList(
                new User("Lollo", "Lorenzo", "Ghiglielmi", "lorenzo@ghiglielmi.com"),
                new User("Anna", "Anna", "Rossi", "anna.rossi@example.com"),
                new User("Marco", "Marco", "Bianchi", "marco.bianchi@example.com"),
                new User("Giulia", "Giulia", "Verdi", "giulia.verdi@example.com"),
                new User("Francesco", "Francesco", "Neri", "francesco.neri@example.com"),
                new User("Elena", "Elena", "Russo", "elena.russo@example.com"),
                new User("Davide", "Davide", "Gallo", "davide.gallo@example.com"),
                new User("Chiara", "Chiara", "Fontana", "chiara.fontana@example.com"),
                new User("Alessandro", "Alessandro", "Monti", "alessandro.monti@example.com"),
                new User("Federica", "Federica", "Ricci", "federica.ricci@example.com")
        );

        for (User user : userList) {
            userService.postUser(user);
        }

        LocalDate date1 = LocalDate.of(2024, 5, 18);
        LocalDate date2 = LocalDate.of(2024, 5, 19);
        LocalDate date3 = LocalDate.of(2024, 5, 20);

        List<Reservation> reservationList = Arrays.asList(
                new Reservation(userList.get(1), officeList.get(1), date1, date1.plusDays(1)),
                new Reservation(userList.get(0), officeList.get(0), date1, date1.plusDays(1)),
                new Reservation(userList.get(2), officeList.get(2), date1, date1.plusDays(1)),
                new Reservation(userList.get(3), officeList.get(3), date1, date1.plusDays(1)),
                new Reservation(userList.get(4), officeList.get(4), date1, date1.plusDays(1)),
                new Reservation(userList.get(5), officeList.get(5), date1, date1.plusDays(1)),
                new Reservation(userList.get(6), officeList.get(6), date1, date1.plusDays(1)),
                new Reservation(userList.get(7), officeList.get(7), date1, date1.plusDays(1)),
                new Reservation(userList.get(8), officeList.get(8), date1, date1.plusDays(1)),
                new Reservation(userList.get(9), officeList.get(9), date1, date1.plusDays(1)),

                new Reservation(userList.get(0), officeList.get(10), date2, date2.plusDays(1)),
                new Reservation(userList.get(1), officeList.get(11), date2, date2.plusDays(1)),
                new Reservation(userList.get(2), officeList.get(12), date2, date2.plusDays(1)),
                new Reservation(userList.get(3), officeList.get(13), date2, date2.plusDays(1)),
                new Reservation(userList.get(4), officeList.get(14), date2, date2.plusDays(1)),
                new Reservation(userList.get(5), officeList.get(15), date2, date2.plusDays(1)),
                new Reservation(userList.get(6), officeList.get(16), date2, date2.plusDays(1)),
                new Reservation(userList.get(7), officeList.get(17), date2, date2.plusDays(1)),
                new Reservation(userList.get(8), officeList.get(18), date2, date2.plusDays(1)),
                new Reservation(userList.get(9), officeList.get(19), date2, date2.plusDays(1)),

                new Reservation(userList.get(0), officeList.get(20), date3, date3.plusDays(1)),
                new Reservation(userList.get(1), officeList.get(21), date3, date3.plusDays(1)),
                new Reservation(userList.get(2), officeList.get(22), date3, date3.plusDays(1)),
                new Reservation(userList.get(3), officeList.get(23), date3, date3.plusDays(1)),
                new Reservation(userList.get(4), officeList.get(24), date3, date3.plusDays(1)),
                new Reservation(userList.get(5), officeList.get(25), date3, date3.plusDays(1)),
                new Reservation(userList.get(6), officeList.get(26), date3, date3.plusDays(1)),
                new Reservation(userList.get(7), officeList.get(27), date3, date3.plusDays(1)),
                new Reservation(userList.get(8), officeList.get(28), date3, date3.plusDays(1)),
                new Reservation(userList.get(9), officeList.get(29), date3, date3.plusDays(1))
        );


        for (Reservation reservation : reservationList) {
            reservationService.postReservation(reservation);
        }
    }

}

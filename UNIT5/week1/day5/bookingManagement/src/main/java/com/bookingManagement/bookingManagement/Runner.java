package com.bookingManagement.bookingManagement;

import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Enum.OfficeType;
import com.bookingManagement.bookingManagement.Service.BuildingService;
import com.bookingManagement.bookingManagement.Service.OfficeService;
import com.bookingManagement.bookingManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private UserService userService;

    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BookingManagementApplication.class);

        Building[] buildings = {
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
        };

        /*for (Building building : buildings) {
            buildingService.postBuilding(building);
        }*/

        Office[] offices = {
                new Office("Private office with a great view", OfficeType.PRIVATE, 5, buildings[0]),
                new Office("Spacious open space", OfficeType.OPENSPACE, 15, buildings[1]),
                new Office("Meeting room with projector", OfficeType.MEETINGROOM, 8, buildings[2]),
                new Office("Private office with modern design", OfficeType.PRIVATE, 4, buildings[3]),
                new Office("Open space with natural light", OfficeType.OPENSPACE, 20, buildings[4]),
                new Office("Large meeting room", OfficeType.MEETINGROOM, 12, buildings[5]),
                new Office("Cozy private office", OfficeType.PRIVATE, 3, buildings[6]),
                new Office("Open space with flexible desks", OfficeType.OPENSPACE, 25, buildings[7]),
                new Office("Small meeting room", OfficeType.MEETINGROOM, 6, buildings[8]),
                new Office("Private office with balcony", OfficeType.PRIVATE, 5, buildings[9]),
                new Office("Open space with standing desks", OfficeType.OPENSPACE, 18, buildings[0]),
                new Office("Meeting room with video conferencing", OfficeType.MEETINGROOM, 10, buildings[1]),
                new Office("Quiet private office", OfficeType.PRIVATE, 4, buildings[2]),
                new Office("Open space near the window", OfficeType.OPENSPACE, 22, buildings[3]),
                new Office("Medium meeting room", OfficeType.MEETINGROOM, 9, buildings[4]),
                new Office("Private office with ensuite bathroom", OfficeType.PRIVATE, 6, buildings[5]),
                new Office("Bright open space", OfficeType.OPENSPACE, 17, buildings[6]),
                new Office("Meeting room with round table", OfficeType.MEETINGROOM, 8, buildings[7]),
                new Office("Private office with built-in storage", OfficeType.PRIVATE, 5, buildings[8]),
                new Office("Large open space with breakout area", OfficeType.OPENSPACE, 30, buildings[9]),
                new Office("Meeting room with whiteboard", OfficeType.MEETINGROOM, 7, buildings[0]),
                new Office("Executive private office", OfficeType.PRIVATE, 6, buildings[1]),
                new Office("Collaborative open space", OfficeType.OPENSPACE, 20, buildings[2]),
                new Office("Meeting room for workshops", OfficeType.MEETINGROOM, 10, buildings[3]),
                new Office("Small private office", OfficeType.PRIVATE, 2, buildings[4]),
                new Office("Open space with creative zones", OfficeType.OPENSPACE, 25, buildings[5]),
                new Office("Meeting room with breakout area", OfficeType.MEETINGROOM, 9, buildings[6]),
                new Office("Private office with a view", OfficeType.PRIVATE, 5, buildings[7]),
                new Office("Open space with quiet zones", OfficeType.OPENSPACE, 18, buildings[8]),
                new Office("Meeting room with flexible seating", OfficeType.MEETINGROOM, 8, buildings[9])
        };

        User[] users = {
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
        };

        /*for (User user : users) {
            userService.postUser(user);
        }*/

        offices[1].setUserReservation(users[1]);
        offices[3].setUserReservation(users[1]);
        offices[5].setUserReservation(users[1]);
        offices[7].setUserReservation(users[4]);
        offices[9].setUserReservation(users[4]);
        offices[11].setUserReservation(users[6]);
        offices[13].setUserReservation(users[7]);
        offices[15].setUserReservation(users[8]);
        offices[17].setUserReservation(users[9]);
        offices[19].setUserReservation(users[9]);

        /*for (Office office : offices) {
            officeService.postOffice(office);
        }*/


    }

}

package com.bookingManagement.bookingManagement.Entity;

import com.bookingManagement.bookingManagement.Bean.Catalogue;
import com.bookingManagement.bookingManagement.BookingManagementApplication;
import com.bookingManagement.bookingManagement.Entity.Location.Building;
import com.bookingManagement.bookingManagement.Entity.Location.Office;
import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Service.BuildingService;
import com.bookingManagement.bookingManagement.Service.OfficeService;
import com.bookingManagement.bookingManagement.Service.ReservationService;
import com.bookingManagement.bookingManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.bookingManagement.bookingManagement.Enum.OfficeType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {
    @Autowired
private Catalogue catalogue;
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private OfficeService officeService;
    @Autowired

    private ReservationService reservationService;
    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showCatalogue();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    createBuilding();
                    break;
                case 4:
                    createOffice();
                    break;
                case 5:
                    createReservation();
                    break;
                case 6:
                    searchOfficesByTypeAndCity();
                    break;
                case 7:
                    searchReservationByOfficeIdAndDate();
                    break;
                case 8:
                    checkReservationExpiration();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Program terminated.");
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Show Catalogue");
        System.out.println("2 - Create User");
        System.out.println("3 - Create Building");
        System.out.println("4 - Create Office");
        System.out.println("5 - Create Reservation");
        System.out.println("6 - Search offices by type and city");
        System.out.println("7 - Search a reservation by officeId and date");
        System.out.println("8- Check reservation expiration");
        System.out.println("0 - Exit");
    }

    private void showCatalogue() {
        System.out.println(catalogue);
    }

    private void createUser() {
        System.out.println("Insert username:");
        String username = scanner.nextLine();
        System.out.println("Insert name:");
        String name = scanner.nextLine();
        System.out.println("Insert surname:");
        String surname = scanner.nextLine();
        System.out.println("Insert email:");
        String email = scanner.nextLine();

        User user = new User(username, name, surname, email);

        userService.postUser(user);
    }

    private void createBuilding() {
        System.out.println("Insert name:");
        String name = scanner.nextLine();
        System.out.println("Insert address:");
        String address = scanner.nextLine();
        System.out.println("Insert city:");
        String city = scanner.nextLine();

        buildingService.postBuilding(new Building(name, address, city));
    }

    private void createOffice() {
        System.out.println("Insert description:");
        String description = scanner.nextLine();

        System.out.println("""
            Type:
            1 - Private
            2 - Open Space
            3 - Meeting Room""");
        int typeInputNumber = scanner.nextInt();
        OfficeType officeType;
        switch (typeInputNumber) {
            case 1:
                officeType = OfficeType.PRIVATE;
                break;
            case 2:
                officeType = OfficeType.OPENSPACE;
                break;
            case 3:
                officeType = OfficeType.MEETINGROOM;
                break;
            default:
                throw new IllegalArgumentException("Invalid office type. Please select a valid option.");
        }

        System.out.println("Insert max capacity:");
        int maxCapacity = scanner.nextInt();
        System.out.println(catalogue.getBuildings());
        System.out.println("Insert a buildingId:");
        int buildingId = scanner.nextInt();
        scanner.nextLine();
        Building building = buildingService.getBuilding(buildingId);

        officeService.postOffice(new Office(description, officeType, maxCapacity, building));
    }



    private void createReservation() {
        System.out.println(ctx.getBean("Users", List.class));
        System.out.println("Insert userId:");
        int userId = scanner.nextInt();
        User user = userService.getUserById(userId);
        System.out.println(catalogue.getOffices());
        System.out.println("Insert officeId:");
        int officeId = scanner.nextInt();
        Office office = officeService.getOffice(officeId);
        scanner.nextLine();
        System.out.println("Insert a reservation date: (Format YYYY MM DD)");
        String date = scanner.nextLine();
        LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy MM dd"));
        System.out.println("Insert the quantity of partecipants: (max for this office: " + office.getMaxCapacity() + ")");
        int peopleQty = scanner.nextInt();
        scanner.nextLine();

        if(reservationService.isPeopleQtyWithinCapacity(office, peopleQty)){
            reservationService.createReservation(user, office, reservationDate, reservationDate.plusDays(1), peopleQty);
        } else {
            throw new IllegalArgumentException("The amount of people exceeds the capacity of the office. This office max capacity is: " + office.getMaxCapacity() + ".");
        }
  }



    private void searchOfficesByTypeAndCity() {
        System.out.println();
        System.out.println("""
            Type:
            1 - Private
            2 - Open Space
            3 - Meeting Room""");
        int typeInputNumber = scanner.nextInt();
        OfficeType officeType;
        switch (typeInputNumber) {
            case 1:
                officeType = OfficeType.PRIVATE;
                break;
            case 2:
                officeType = OfficeType.OPENSPACE;
                break;
            case 3:
                officeType = OfficeType.MEETINGROOM;
                break;
            default:
                throw new IllegalArgumentException("Invalid office type. Please select a valid option.");
        }
        scanner.nextLine();
        System.out.println("Insert city:");
        String city = scanner.nextLine();

        System.out.println(officeService.findOfficesByTypeAndCity(officeType, city));
    }

    private void searchReservationByOfficeIdAndDate() {
        System.out.println(catalogue.getOffices());
        System.out.println("Insert officeId:");
        int officeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insert a date: (Format YYYY MM DD)");
        String scannedDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(scannedDate, DateTimeFormatter.ofPattern("yyyy MM dd"));

        System.out.println(reservationService.findReservationsByOfficeAndDate(officeId, date));
    }

    private void checkReservationExpiration() {
        System.out.println(ctx.getBean("Reservations", List.class));
        System.out.println("Insert reservationId:");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        Reservation reservation = reservationService.getReservation(reservationId);
        if(reservationService.isReservationExpired(reservation)) {
            System.out.println("The reservations is expired.");
        } else {
            System.out.println("The reservation is still valid.");
        }
    }
}

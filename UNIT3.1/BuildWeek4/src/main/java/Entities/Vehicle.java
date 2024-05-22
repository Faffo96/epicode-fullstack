package Entities;

import Entities.Services.Ticket;
import enums.VehicleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue
    private int vehicleId;

    private int capacity;

    @Enumerated (EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @OneToMany
    private List<Ticket> ticket;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trips;

    @OneToMany
    private List<VehicleState> vehicleState;

    @Column(name = "users_on_board")
    private int usersOnBoard;

    public Vehicle(VehicleType vehicleType, Route route) {
        if (vehicleType == VehicleType.BUS) {
            this.capacity = 5;
        } else if (vehicleType == VehicleType.TRAM) {
            this.capacity = 10;
        }
        this.vehicleType = vehicleType;
        this.route = route;
        this.usersOnBoard = 0;
    }

    public Vehicle(){

    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<VehicleState> getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(List<VehicleState> vehicleState) {
        this.vehicleState = vehicleState;
    }

    public int getUsersOnBoard() {
        return usersOnBoard;
    }

    public void setUsersOnBoard(int usersOnBoard) {
        this.usersOnBoard = usersOnBoard;
    }

    public boolean checkMaxCapacity() {
        if (this.vehicleType == VehicleType.BUS && this.usersOnBoard >= 5) {
            return true;
        } else return this.vehicleType == VehicleType.TRAM && this.usersOnBoard >= 10;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", capacity=" + capacity +
                ", vehicleType=" + vehicleType +
                ", ticket=" + ticket +
                ", route=" + route +
                ", trips=" + trips +
                ", vehicleState=" + vehicleState +
                ", usersOnBoard=" + usersOnBoard +
                '}';
    }
}

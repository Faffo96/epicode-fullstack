package Entities.Services;

import Dao.SellerDao.ShopDao;
import Dao.UserDao;
import Entities.Route;
import Entities.Sellers.Seller;
import Entities.User;
import Entities.Vehicle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket extends Service {

    private LocalDate stampDate;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private boolean validity = true;

    public Ticket(Seller seller, User user) {
        super(seller);
        this.user = user;
    }

    public Ticket() {
    }

    @Override
    public boolean isValidity() {
        return validity;
    }

    @Override
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public LocalDate getStampDate() {
        return stampDate;
    }

    public void setStampDate(LocalDate stampDate) {
        this.stampDate = stampDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
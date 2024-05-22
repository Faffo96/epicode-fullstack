package Entities.Services;
import Entities.Route;
import Entities.Sellers.Seller;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "services")
public abstract class Service {

    @Id
    @GeneratedValue
    protected int id;

    protected LocalDate purchaseDate;
    protected boolean validity = true;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    protected Seller seller;


    public Service(Seller seller) {
        this.purchaseDate = LocalDate.now();
        this.seller = seller;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

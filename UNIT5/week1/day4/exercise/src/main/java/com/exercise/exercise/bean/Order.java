package com.exercise.exercise.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.exercise.exercise.ENUM.OrderState;
import jakarta.annotation.Generated;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name = "orders")
@Component
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    private OrderState orderState;
    private int orderSeatsNumber;
    private LocalDateTime date;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable diningTable;

    @Value("${order.seatPrice}")
    private String seatPriceStr;

    private double seatPrice;

    @PostConstruct
    private void init() {
        try {
            seatPrice = Double.parseDouble(seatPriceStr);
            if (seatPrice <= 0) {
                throw new IllegalArgumentException("Invalid seatPrice value: " + seatPrice);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid seatPrice format: " + seatPriceStr, e);
        }
    }

    public double calculateTotal(int clientQuantity) {
        return this.products.stream()
                .mapToDouble(Product::getPrice)
                .sum() + this.seatPrice * clientQuantity;
    }
}

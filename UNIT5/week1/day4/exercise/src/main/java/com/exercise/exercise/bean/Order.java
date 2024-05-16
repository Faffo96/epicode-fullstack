package com.exercise.exercise.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.exercise.exercise.ENUM.OrderState;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Order {
    private int orderId;
    private List<Product> products;
    private OrderState orderState;
    private int orderSeatsNumber;
    private LocalDateTime date;
    private double totalPrice;
    private Table table;

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

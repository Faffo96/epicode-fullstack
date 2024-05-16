package com.exercise.exercise.repository;

import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Product;
import com.exercise.exercise.bean.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT t FROM Topping t " +
            "JOIN t.pizzas p " +
            "WHERE p.id = :pizzaId")
    List<Topping> getToppingsByPizzaId(@Param("pizzaId") int pizzaId);

    @Query("SELECT t FROM Pizza p " +
            "JOIN p.toppings t " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :pizzaPartialName, '%'))")
    List<Topping> getToppingsByPizzaPartialName(@Param("pizzaPartialName") String pizzaPartialName);


}

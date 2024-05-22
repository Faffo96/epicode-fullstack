package com.exercise.exercise.repository;

import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    /*@Query(value = "SELECT p FROM Pizza p JOIN FETCH OrderProduct op ON p.id = op.pizza.id JOIN FETCH op.order o WHERE o.id = :orderId", nativeQuery = true)
    List<Object[]> getPizzasByOrderId(@Param("orderId") int orderId);*/

    /*@Query("SELECT p FROM Pizza p JOIN p.orders o WHERE o.orderId = :orderId")
    List<Pizza> getPizzasByOrderId(@Param("orderId") int orderId);*/
}



package com.exercise.exercise.repository;

import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT p.* FROM pizzas p JOIN order_product op ON p.id = op.product_id JOIN orders o ON op.order_id = o.order_id WHERE o.order_id = :orderId", nativeQuery = true)
    List<Object[]> getPizzasByOrderId(@Param("orderId") int orderId);

    /*@Query(value = "SELECT p FROM Pizza p JOIN p.orders o WHERE o.orderId = :orderId")
    List<Pizza> getPizzasByOrderId(@Param("orderId") int orderId);*/
}



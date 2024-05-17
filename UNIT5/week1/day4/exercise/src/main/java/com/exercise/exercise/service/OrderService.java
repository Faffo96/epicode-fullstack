package com.exercise.exercise.service;

import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Product;
import com.exercise.exercise.repository.OrderRepository;
import com.exercise.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void addProduct(Order order){
        orderRepository.save(order);
    }

    public Order getOrder(int id){
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    /*public List<Object[]> getPizzasByOrderId(int orderId) {
        return orderRepository.getPizzasByOrderId(orderId);
    }*/
}

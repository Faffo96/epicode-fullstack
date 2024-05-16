package com.exercise.exercise.service;

import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Product;
import com.exercise.exercise.bean.Topping;
import com.exercise.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Product getProduct(int id){
        return productRepository.findById(id).get();
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Topping> getToppingsByPizzaId(int id) {
        return productRepository.getToppingsByPizzaId(id);
    }

    public List<Topping> getToppingsByPizzaPartialName(String pizzaPartialName) {
        return productRepository.getToppingsByPizzaPartialName(pizzaPartialName);
    }
}

package com.exercise.exercise.bean;

import com.exercise.exercise.appConfig.AppConfig;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Data
@ToString(callSuper = true)
public class Pizza extends Product {
    private List<Topping> toppings;
}

package com.exercise.exercise;

import com.exercise.exercise.appConfig.AppConfig;
import com.exercise.exercise.bean.*;
import com.exercise.exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private ProductService productService;


    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ExerciseApplication.class);

        Menu menu = ctx.getBean("Menu", Menu.class);

        System.out.println(menu);

        Table table1 = ctx.getBean("Table1", Table.class);
        Order orderTable1 = ctx.getBean("OrderTable1", Order.class);
        orderTable1.setTable(table1);

        Table table2 = ctx.getBean("Table2", Table.class);
        Order orderTable2 = ctx.getBean("OrderTable2", Order.class);
        orderTable2.setTable(table2);

        /*Topping t1 = ctx.getBean("TomatoSauce", Topping.class);
        productService.addProduct(t1);

        Topping t2 = ctx.getBean("Mozzarella", Topping.class);
        productService.addProduct(t2);

        Topping t3 = ctx.getBean("Cheese", Topping.class);
        productService.addProduct(t3);

        Topping t4 = ctx.getBean("Ham", Topping.class);
        productService.addProduct(t4);

        Topping t5 = ctx.getBean("Onions", Topping.class);
        productService.addProduct(t5);

        Topping t6 = ctx.getBean("Salami", Topping.class);
        productService.addProduct(t6);

        Topping t7 = ctx.getBean("Pineapple", Topping.class);
        productService.addProduct(t7);

        Pizza p1 = ctx.getBean("MargheritaPizza", Pizza.class);
        productService.addProduct(p1);

        Pizza p2 = ctx.getBean("HawaiianPizza", Pizza.class);
        productService.addProduct(p2);

        Pizza p3 = ctx.getBean("SalamiPizza", Pizza.class);
        productService.addProduct(p3);

        Drink d1 = ctx.getBean("Wine", Drink.class);
        productService.addProduct(d1);

        Drink d2 = ctx.getBean("Water", Drink.class);
        productService.addProduct(d2);

        Drink d3 = ctx.getBean("Lemonade", Drink.class);
        productService.addProduct(d3);*/

        System.out.println(productService.getToppingsByPizzaId(8));

        System.out.println("Pizza MARGHERITA:");

        System.out.println(productService.getToppingsByPizzaPartialName("Marg"));

        /*List<Topping> toppings = menu.getToppings();
        for (int i = 0; i < toppings.size(); i++) {
            productService.addProduct(toppings.get(i));
        }

        List<Drink> drinks = menu.getDrinks();
        for (int i = 0; i < drinks.size(); i++) {
            productService.addProduct(drinks.get(i));
        }

        List<Pizza> pizzas = menu.getPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            productService.addProduct(pizzas.get(i));
        }*/



        System.out.println(table1);
        System.out.println(table2);
    }
}

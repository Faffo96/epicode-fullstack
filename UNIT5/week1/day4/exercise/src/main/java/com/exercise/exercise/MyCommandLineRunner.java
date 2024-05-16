package com.exercise.exercise;

import com.exercise.exercise.bean.*;
import com.exercise.exercise.service.DiningTableService;
import com.exercise.exercise.service.OrderService;
import com.exercise.exercise.service.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiningTableService diningTableService;


    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ExerciseApplication.class);

        Menu menu = ctx.getBean("Menu", Menu.class);

        System.out.println(menu);

        DiningTable diningTable1 = ctx.getBean("Table1", DiningTable.class);
        Order orderTable1 = ctx.getBean("OrderTable1", Order.class);
        List<Order> orders1 = new ArrayList<>();
        orders1.add(orderTable1);
        diningTable1.setOrders(orders1);

        DiningTable diningTable2 = ctx.getBean("Table2", DiningTable.class);
        Order orderTable2 = ctx.getBean("OrderTable2", Order.class);
        List<Order> orders2 = new ArrayList<>();
        orders2.add(orderTable2);
        diningTable2.setOrders(orders2);

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
        productService.addProduct(d3);

        diningTableService.addProduct(diningTable1);
        diningTableService.addProduct(diningTable2);
        orderService.addProduct(orderTable1);
        orderService.addProduct(orderTable2);*/

        System.out.println(productService.getToppingsByPizzaId(8));

        System.out.println("Pizza MARGHERITA:");

        System.out.println(productService.getToppingsByPizzaPartialName("Marg"));

        System.out.println(orderService.getPizzasByOrderId(2));

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


        System.out.println(diningTable1);
        System.out.println(diningTable2);
    }
}

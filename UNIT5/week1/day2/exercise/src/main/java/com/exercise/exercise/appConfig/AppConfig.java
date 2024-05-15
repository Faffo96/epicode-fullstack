package com.exercise.exercise.appConfig;

import com.exercise.exercise.ENUM.OrderState;
import com.exercise.exercise.bean.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean("Cheese")
    public Topping cheese() {
        Topping cheese = new Topping();
        cheese.setName("Cheese");
        cheese.setCalories(92);
        cheese.setPrice(0.69);
        return cheese;
    }

    @Bean("Ham")
    public Topping ham() {
        Topping ham = new Topping();
        ham.setName("Ham");
        ham.setCalories(35);
        ham.setPrice(0.99);
        return ham;
    }

    @Bean("Onions")
    public Topping onions() {
        Topping onions = new Topping();
        onions.setName("Onions");
        onions.setCalories(22);
        onions.setPrice(0.69);
        return onions;
    }

    @Bean("Pineapple")
    public Topping pineapple() {
        Topping pineapple = new Topping();
        pineapple.setName("Pineapple");
        pineapple.setCalories(24);
        pineapple.setPrice(0.79);
        return pineapple;
    }

    @Bean("Salami")
    public Topping salami() {
        Topping salami = new Topping();
        salami.setName("Salami");
        salami.setCalories(86);
        salami.setPrice(0.99);
        return salami;
    }

    @Bean("TomatoSauce")
    public Topping tomatoSauce() {
        Topping tomatoSauce = new Topping();
        tomatoSauce.setName("Tomato Sauce");
        tomatoSauce.setCalories(86);
        return tomatoSauce;
    }

    @Bean("Mozzarella")
    public Topping mozzarella() {
        Topping mozzarella = new Topping();
        mozzarella.setName("Mozzarella");
        mozzarella.setCalories(106);
        mozzarella.setPrice(0.30);
        return mozzarella;
    }

    @Bean("MargheritaPizza")
    public Pizza margherita() {
        Pizza margherita = new Pizza();
        margherita.setName("Margherita Pizza");
        margherita.setCalories(1104);
        margherita.setPrice(4.99);
        margherita.setToppings(List.of(tomatoSauce(), mozzarella()));
        return margherita;
    }

    @Bean("HawaiianPizza")
    public Pizza hawaiian() {
        Pizza hawaiian = new Pizza();
        hawaiian.setName("Hawaiian Pizza");
        hawaiian.setCalories(1024);
        hawaiian.setPrice(6.49);
        hawaiian.setToppings(List.of(tomatoSauce(), mozzarella(), ham(), pineapple()));
        return hawaiian;
    }

    @Bean("SalamiPizza")
    public Pizza salamiPizza() {
        Pizza salami = new Pizza();
        salami.setName("Salami Pizza");
        salami.setCalories(1160);
        salami.setPrice(5.99);
        salami.setToppings(List.of(tomatoSauce(), mozzarella(), salami()));
        return salami;
    }

    @Bean("Lemonade")
    public Drink lemonade() {
        Drink lemonade = new Drink();
        lemonade.setName("Lemonade");
        lemonade.setLiters(0.33);
        lemonade.setCalories(128);
        lemonade.setPrice(1.29);
        return lemonade;
    }

    @Bean("Water")
    public Drink water() {
        Drink water = new Drink();
        water.setName("Water");
        water.setLiters(0.5);
        water.setCalories(0);
        water.setPrice(1.29);
        return water;
    }

    @Bean("Wine")
    public Drink wine() {
        Drink wine = new Drink();
        wine.setName("Wine");
        wine.setLiters(0.75);
        wine.setCalories(607);
        wine.setPrice(7.49);
        wine.setVol(13);
        return wine;
    }

    @Bean("Menu")
    public Menu menu() {
        Menu menu = new Menu();
        menu.setDrinks(List.of(water(), lemonade(), wine()));
        menu.setToppings(List.of(cheese(), ham(), onions(), pineapple(), salami()));
        menu.setPizzas(List.of(margherita(), hawaiian(), salamiPizza()));
        return menu;
    }

    @Bean("Table1")
    public Table table1() {
        Table table = new Table();
        table.setBusy(true);
        table.setMaxSeatsNumber(2);
        return table;
    }

    @Bean("OrderTable1")
    public Order orderTable1() {
        int clientQuantity = 1;
        if (clientQuantity <= table1().getMaxSeatsNumber()) {
            Order orderTable = new Order();
            orderTable.setOrderState(OrderState.INPROGRESS);
            orderTable.setDate(LocalDateTime.now());
            orderTable.setOrderSeatsNumber(clientQuantity);
            orderTable.setProducts(List.of(salamiPizza(), wine()));
            double totalPrice = orderTable.calculateTotal(clientQuantity);
            orderTable.setTotalPrice(totalPrice);
            return orderTable;
        }
        return null;
    }

    @Bean("Table2")
    public Table table2() {
        Table table = new Table();
        table.setTableId(1);
        table.setBusy(true);
        table.setMaxSeatsNumber(3);
        return table;
    }

    @Bean("OrderTable2")
    public Order orderTable2() {
        int clientQuantity = 3;
        if (clientQuantity <= table2().getMaxSeatsNumber()) {
            Order orderTable = new Order();
            orderTable.setOrderId(1);
            orderTable.setOrderState(OrderState.SERVED);
            orderTable.setDate(LocalDateTime.now());
            orderTable.setOrderSeatsNumber(clientQuantity);
            orderTable.setProducts(List.of(margherita(), margherita(), salamiPizza(), wine(), water()));
            double totalPrice = orderTable.calculateTotal(clientQuantity);
            orderTable.setTotalPrice(totalPrice);
            return orderTable;
        }
        return null;
    }

    @Bean("Table3")
    public Table table3() {
        Table table = new Table();
        table.setBusy(false);
        table.setMaxSeatsNumber(4);
        return table;
    }

    @Bean("Table4")
    public Table table4() {
        Table table = new Table();
        table.setBusy(false);
        table.setMaxSeatsNumber(5);
        return table;
    }

}

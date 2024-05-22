package com.exercise.exercise.appConfig;

import com.exercise.exercise.bean.Drink;
import com.exercise.exercise.bean.Menu;
import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Topping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
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

}

package com.exercise.exercise;

import com.exercise.exercise.appConfig.AppConfig;
import com.exercise.exercise.bean.Menu;
import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Table;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ExerciseApplication.class);

        Menu menu = ctx.getBean("Menu", Menu.class);

        Table table1 = ctx.getBean("Table1", Table.class);
        Order orderTable1 = ctx.getBean("OrderTable1", Order.class);
        table1.setOrder(orderTable1);

        Table table2 = ctx.getBean("Table2", Table.class);
        Order orderTable2 = ctx.getBean("OrderTable2", Order.class);
        table2.setOrder(orderTable2);

        System.out.println(table1);
        System.out.println(table2);
    }
}

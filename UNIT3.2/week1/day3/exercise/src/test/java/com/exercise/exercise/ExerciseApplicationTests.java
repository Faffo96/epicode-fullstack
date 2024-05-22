package com.exercise.exercise;

import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Pizza;
import com.exercise.exercise.bean.Table;
import com.exercise.exercise.bean.Topping;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class ExerciseApplicationTests {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(ExerciseApplication.class);

	@Test
	public void testMargheritaPizzaBeanCreation() {
		Pizza margheritaPizza = ctx.getBean("MargheritaPizza", Pizza.class);
		assertNotNull("MargheritaPizza bean should not be null", margheritaPizza);
	}

	@Test
	public void testCheeseBeanCreation() {
		Topping cheese = ctx.getBean("Cheese", Topping.class);
		assertEquals("Cheese", cheese.getName());
	}

	@Test
	public void testTable1Creation() {
		Table table1 = ctx.getBean("Table1", Table.class);
		assertNotNull("Table1 bean should not be null", table1);
		assertTrue("Table1 should be busy", table1.isBusy());
	}

	@Test
	public void testOrderTable1Creation() {
		Order orderTable1 = ctx.getBean("OrderTable1", Order.class);
		assertNotNull("OrderTable1 bean should not be null", orderTable1);
	}

	@Test
	public void testOrderTotalCalculation() {
		Order order = ctx.getBean("OrderTable1", Order.class);

		double totalPrice = order.calculateTotal(1);

		assertEquals(15.48, totalPrice);
	}
}

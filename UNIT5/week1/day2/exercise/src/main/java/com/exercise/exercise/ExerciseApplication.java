package com.exercise.exercise;

import com.exercise.exercise.appConfig.AppConfig;
import com.exercise.exercise.bean.Menu;
import com.exercise.exercise.bean.Order;
import com.exercise.exercise.bean.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

}

package it.epicode.progettoSpring;

import it.epicode.progettoSpring.appConfig.AppConfig;
import it.epicode.progettoSpring.bean.Studente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProgettoSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProgettoSpringApplication.class, args);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		Studente studente = ctx.getBean("Fabio", Studente.class);

		Studente studente2 = ctx.getBean("Mario", Studente.class);

		System.out.println(studente);
		System.out.println(studente2);
	}
}

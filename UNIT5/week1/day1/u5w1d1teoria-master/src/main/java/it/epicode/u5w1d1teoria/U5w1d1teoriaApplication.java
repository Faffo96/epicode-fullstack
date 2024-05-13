package it.epicode.u5w1d1teoria;

import it.epicode.u5w1d1teoria.appConfig.AppConfig;
import it.epicode.u5w1d1teoria.bean.Aula;
import it.epicode.u5w1d1teoria.bean.Studente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class U5w1d1teoriaApplication {

	public static void main(String[] args) {

		SpringApplication.run(U5w1d1teoriaApplication.class, args);

		//accediamo al contesto di Spring
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		//recuperiamo dal contesto il bean creato da Spring
		Studente studente = ctx.getBean(Studente.class);

		Studente studente2 = ctx.getBean(Studente.class);

		studente.setCognome("Rossi");

		System.out.println(studente==studente2);

		System.out.println(studente);
		System.out.println(studente2);

		Aula aula = ctx.getBean(Aula.class);
		System.out.println(aula);
	}

}

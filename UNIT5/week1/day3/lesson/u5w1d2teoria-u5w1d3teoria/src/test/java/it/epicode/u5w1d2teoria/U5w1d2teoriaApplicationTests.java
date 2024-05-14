package it.epicode.u5w1d2teoria;

import it.epicode.u5w1d2teoria.bean.Aula;
import it.epicode.u5w1d2teoria.bean.Studente;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class U5w1d2teoriaApplicationTests {

	static ApplicationContext ctx;

	@BeforeAll
	public static void accediAlContesto(){
		ctx = new AnnotationConfigApplicationContext(U5w1d2teoriaApplication.class);
		System.out.println("Accesso al contesto avvenuto");
	}

	@Test
	void verificaNomePrimoStudente() {
		Studente s1 = ctx.getBean("Carla", Studente.class);
		Assertions.assertEquals("Carla", s1.getNome());
	}

	@Test
	public void verificaAulaNotNull(){
		Aula aula = ctx.getBean(Aula.class);
		Assertions.assertNotNull(aula);
	}
	@Test
	public void verificaNumeroStudentiInAula(){
		Aula aula = ctx.getBean(Aula.class);
		Assertions.assertEquals(2, aula.getStudenti().size());
	}

	@AfterAll
	public static void chiudiContesto(){
		ctx = null;
		System.out.println("Contesto chiuso");
	}

}

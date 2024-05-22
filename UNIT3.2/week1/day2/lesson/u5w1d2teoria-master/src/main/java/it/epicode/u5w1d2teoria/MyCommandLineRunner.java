package it.epicode.u5w1d2teoria;

import it.epicode.u5w1d2teoria.bean.Aula;
import it.epicode.u5w1d2teoria.bean.Dispositivo;
import it.epicode.u5w1d2teoria.bean.Scuola;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(U5w1d2teoriaApplication.class);

        Aula aula = ctx.getBean(Aula.class);
        System.out.println(aula);

        Dispositivo dispositivo=ctx.getBean("smartphone", Dispositivo.class);
        System.out.println(dispositivo);

        Scuola scuola = ctx.getBean(Scuola.class);

        System.out.println(scuola);
    }
}

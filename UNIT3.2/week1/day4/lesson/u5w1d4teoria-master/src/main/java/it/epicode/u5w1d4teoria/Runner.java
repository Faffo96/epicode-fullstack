package it.epicode.u5w1d4teoria;

import it.epicode.u5w1d4teoria.bean.Computer;
import it.epicode.u5w1d4teoria.bean.Smartphone;
import it.epicode.u5w1d4teoria.bean.Studente;
import it.epicode.u5w1d4teoria.service.DispositivoService;
import it.epicode.u5w1d4teoria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private StudenteService studenteService;
    @Autowired
    private DispositivoService dispositivoService;

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(U5w1d4teoriaApplication.class);

//        Studente studente1 = ctx.getBean("Carla", Studente.class);
//        studenteService.inserisciStudente(studente1);
//
//        Studente studente2 = ctx.getBean("Francesca", Studente.class);
//        studenteService.inserisciStudente(studente2);
//
//        Computer computer = ctx.getBean(Computer.class);
//        computer.setStudente(studente1);
//        dispositivoService.inserisciDispositivo(computer);
//
//        Smartphone smartphone = ctx.getBean(Smartphone.class);
//        smartphone.setStudente(studente2);
//        dispositivoService.inserisciDispositivo(smartphone);

        System.out.println(studenteService.getStudentiByNome("Carla"));
        System.out.println(studenteService.getStudentiByPartialNome("%a%"));

        System.out.println(dispositivoService.getComputerByRamLessThan(8));

        System.out.println(dispositivoService.getDispositiviOrderByNome());
    }
}

package it.epicode.u5w1d1teoria.appConfig;


import it.epicode.u5w1d1teoria.bean.Aula;
import it.epicode.u5w1d1teoria.bean.Computer;
import it.epicode.u5w1d1teoria.bean.Smartphone;
import it.epicode.u5w1d1teoria.bean.Studente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    //@Scope("prototype")
    public Studente getStudente(){
        Studente studente = new Studente();
        studente.setNome("Carla");
        studente.setCognome("Sordi");
        studente.setIndirizzo("Via Roma");
        studente.setDispositivi(List.of(getComputer(), getSmartphone()));
        return studente;
    }
    @Bean
    public Studente getStudente2(){
        Studente studente = new Studente();
        studente.setNome("Francesca");
        studente.setCognome("Polli");
        studente.setIndirizzo("Via Roma");
        return studente;
    }
    @Bean
    public Computer getComputer(){
        Computer computer = new Computer();
        computer.setNome("X1");
        computer.setMarca("HP");
        computer.setCpu("Intel");
        computer.setRam(16);
        computer.setMonitor(27);
        return computer;
    }

    @Bean
    public Smartphone getSmartphone(){
        Smartphone smartphone = new Smartphone();
        smartphone.setNome("S23");
        smartphone.setMarca("Samsung");
        smartphone.setSchermo(6);
        return smartphone;
    }
    @Bean
    public Aula getAula(){
        Aula aula = new Aula();
        aula.setNome("A1");
        aula.setStudenti(List.of(getStudente(), getStudente2()));
        return aula;
    }
}

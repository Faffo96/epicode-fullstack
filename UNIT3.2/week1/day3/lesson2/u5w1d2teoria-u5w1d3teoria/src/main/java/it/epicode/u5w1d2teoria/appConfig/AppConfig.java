package it.epicode.u5w1d2teoria.appConfig;



import it.epicode.u5w1d2teoria.bean.Aula;
import it.epicode.u5w1d2teoria.bean.Computer;
import it.epicode.u5w1d2teoria.bean.Smartphone;
import it.epicode.u5w1d2teoria.bean.Studente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean("Carla")
    @Primary
    //@Scope("prototype")
    public Studente getStudente(@Value("${studente1.nome}") String nome, @Value("${studente1.cognome}") String cognome){
        Studente studente = new Studente();
        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setIndirizzo("Via Roma");

        studente.setDispositivi(List.of(getComputer(), getSmartphone()));
        return studente;
    }
    @Bean("Francesca")
    public Studente getStudente2(){
        Studente studente = new Studente();
        studente.setNome("Francesca");
        studente.setCognome("Polli");
        studente.setIndirizzo("Via Roma");
        return studente;
    }
    @Bean("computer")

    public Computer getComputer(){
        Computer computer = new Computer();
        computer.setNome("X1");
        computer.setMarca("HP");
        computer.setCpu("Intel");
        computer.setRam(16);
        computer.setMonitor(27);
        return computer;
    }

    @Bean("smartphone")
    public Smartphone getSmartphone(){
        Smartphone smartphone = new Smartphone();
        smartphone.setNome("S23");
        smartphone.setMarca("Samsung");
        smartphone.setSchermo(6);
        return smartphone;
    }
    //@Bean
//    public Aula getAula(){
//        Aula aula = new Aula();
//        aula.setNome("A1");
//        aula.setStudenti(List.of(getStudente(), getStudente2()));
//        return aula;
//    }
}

package it.epicode.progettoSpring.appConfig;

import it.epicode.progettoSpring.bean.Studente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("Fabio")
    public Studente getStudente() {
        Studente studente = new Studente("Fabio", "Scaramozzino", "Via Roma");
        return studente;
    }

    @Bean("Mario")
    public Studente getStudente2() {
        Studente studente = new Studente("Mario", "Rossi", "Via Venezia");
        return studente;
    }
}

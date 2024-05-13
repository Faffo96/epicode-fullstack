package it.epicode.u5w1d1teoria.bean;

import lombok.Data;

import java.util.List;
@Data
public class Aula {
    private String nome;
    private List<Studente> studenti;
}

package it.epicode.u5w1d4teoria.service;

import it.epicode.u5w1d4teoria.bean.Studente;
import it.epicode.u5w1d4teoria.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteService {
    @Autowired
    private StudenteRepository studenteRepository;

    public void inserisciStudente(Studente studente){
        studenteRepository.save(studente);
    }

    public Studente getStudente(int matricola){
        return studenteRepository.findById(matricola).get();
    }

    public List<Studente> getStudenti(){
        return studenteRepository.findAll();
    }

    public void cancellaStudente(int matricola){
        studenteRepository.deleteById(matricola);
    }

    public List<Studente> getStudentiByNome(String nome){
        return studenteRepository.findByNome(nome);
    }

    public List<Studente> getStudentiByPartialNome(String nome){
        return studenteRepository.findByNomeLike(nome);
    }
}

package it.epicode.u5w1d4teoria.service;

import it.epicode.u5w1d4teoria.bean.Computer;
import it.epicode.u5w1d4teoria.bean.Dispositivo;
import it.epicode.u5w1d4teoria.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    public void inserisciDispositivo(Dispositivo dispositivo){
        dispositivoRepository.save(dispositivo);
    }

    public Dispositivo getDispositivo(int id){
        return dispositivoRepository.findById(id).get();
    }

    public List<Dispositivo> getDispositivi(){
        return dispositivoRepository.findAll();
    }

    public void cancellaDispositivo(int id){
        dispositivoRepository.deleteById(id);
    }

    public List<Computer> getComputerByRamLessThan(int ram){
        return dispositivoRepository.findByRamLessThan(ram);
    }

    public List<Dispositivo> getDispositiviOrderByNome(){
        return dispositivoRepository.findAllOrderByNomeDesc();
    }
}

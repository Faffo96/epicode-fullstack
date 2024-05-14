package it.epicode.u5w1d2teoria.bean;


import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Computer extends Dispositivo {

    private int monitor;
    private String cpu;
    private int ram;


}

package com.company.Company.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pcs")
public class Pc extends Device {
    private int ram;
    private String gpu;
    private String cpu;
    private Boolean laptop;
}

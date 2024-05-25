package com.company.Company.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Smartphones")
public class Smartphone extends Device {
    private boolean isDualSim;
}

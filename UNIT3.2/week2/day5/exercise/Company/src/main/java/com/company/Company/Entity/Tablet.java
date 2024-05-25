package com.company.Company.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tablets")
public class Tablet extends Device {
    private Boolean haveSimSlot;
}

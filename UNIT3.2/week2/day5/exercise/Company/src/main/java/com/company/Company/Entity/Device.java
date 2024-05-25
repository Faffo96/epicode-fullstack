package com.company.Company.Entity;

import com.company.Company.Enum.DeviceState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Device {
    @Id
    @GeneratedValue
    @JoinColumn(name = "device_id")
    private int deviceId;
    private String brand;
    private String model;
    @JoinColumn(name = "storage_gb")
    private int storageGb;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "device_state")
    private DeviceState deviceState;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}

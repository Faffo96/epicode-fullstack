package com.company.Company.Dto;

import com.company.Company.Entity.Employee;
import com.company.Company.Enum.DeviceState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TabletDto {
    @NotBlank(message = "Il marchio non può essere vuoto")
    @Size(min = 2, max = 100)
    private String brand;

    @NotBlank(message = "Il modello non può essere vuoto")
    @Size(min = 2, max = 100)
    private String model;

    @Positive(message = "La capacità di archiviazione deve essere positiva")
    private int storageGb;

    @NotNull(message = "Lo stato del dispositivo non può essere nullo")
    private DeviceState deviceState;

    private Employee employee;

    @NotNull(message = "Indicare se ha uno slot SIM o meno")
    private Boolean haveSimSlot;
}
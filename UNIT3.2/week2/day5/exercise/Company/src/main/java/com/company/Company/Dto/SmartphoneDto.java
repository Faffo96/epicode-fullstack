package com.company.Company.Dto;

import com.company.Company.Entity.Employee;
import com.company.Company.Enum.DeviceState;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
public class SmartphoneDto {
    @NotBlank
    @Size(min = 2, max = 50)
    private String brand;

    @NotBlank
    @Size(min = 2, max = 50)
    private String model;

    @Positive
    private int storageGb;

    @NotNull
    private DeviceState deviceState;

    private Employee employee;

    private boolean isDualSim;
}
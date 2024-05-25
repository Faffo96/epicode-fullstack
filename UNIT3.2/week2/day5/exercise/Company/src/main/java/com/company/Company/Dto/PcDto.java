package com.company.Company.Dto;

import com.company.Company.Entity.Employee;
import com.company.Company.Enum.DeviceState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PcDto {
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

    @Positive
    @Positive
    private int ram;

    @NotBlank
    @Size(min = 2, max = 100)
    private String gpu;

    @NotBlank
    @Size(min = 2, max = 100)
    private String cpu;

    @NotNull
    private Boolean laptop;
}
package com.company.Company.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class EmployeeDto {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String surname;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String avatar;
}

package com.company.Company.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

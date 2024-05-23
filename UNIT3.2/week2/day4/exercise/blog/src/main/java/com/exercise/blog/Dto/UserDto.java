package com.exercise.blog.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    @NotBlank
    @Size(min = 3, max = 30)
    private String surname;
    @Email
    @NotBlank
    private String email;
    @Past
    private LocalDate birthDate;
    @NotBlank
    @Size(min = 3, max = 30)
    private String avatar;
}

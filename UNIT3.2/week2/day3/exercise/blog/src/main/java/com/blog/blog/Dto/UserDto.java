package com.blog.blog.Dto;

import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;
}

package com.epicode.events_new.Dto;

import com.epicode.events_new.Entity.Event;
import com.epicode.events_new.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    @NotNull
    @Size(min=2, max = 50)
    private String name;

    @NotNull
    @Size(min=2, max = 50)
    private String surname;

    @NotNull
    @Email
    @Size(min=10, max = 50)
    private String email;

    @Size(max = 300)
    private String avatar;

    @NotNull
    @Size(min=8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "La password deve contenere almeno 8 caratteri, almeno una lettera e almeno un numero.")
    private String password;
}



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
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String avatar;
    private String password;
    private UserRole userRole;
    private List<Event> events;
}


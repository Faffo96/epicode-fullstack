package com.epicode.events_new.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private LocalDate date;
    private String location;
    @ManyToMany
    @JoinTable(
            name = "event_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users = new ArrayList<>();
    @JoinColumn(name = "partecipants_limit")
    private int partecipantsLimit;
}

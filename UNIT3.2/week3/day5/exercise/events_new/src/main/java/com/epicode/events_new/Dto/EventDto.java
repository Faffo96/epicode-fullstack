package com.epicode.events_new.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDto {
    @NotNull
    @Size(min=2, max = 100)
    private String name;
    @NotNull
    @Size(min=10, max = 600)
    private String description;
    @FutureOrPresent
    private LocalDate date;
    @NotNull
    @Size(min=2, max = 100)
    private String location;
    private List<String> users;
    @NotNull
    @Positive
    private int participantsLimit;

    @Override
    public String toString() {
        return "EventDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", partecipantsLimit=" + participantsLimit +
                '}';
    }
}

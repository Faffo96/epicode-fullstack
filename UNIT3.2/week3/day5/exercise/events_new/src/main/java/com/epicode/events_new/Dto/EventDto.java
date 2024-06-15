package com.epicode.events_new.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDto {
    private String name;
    private String description;
    private LocalDate date;
    private String location;
    private List<String> users;
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

package com.blog.blog.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int userId;
    private static int count;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public User(String name, String surname, String email, LocalDate birthDate, String avatar) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.avatar = avatar;
        count++;
        this.userId = count;
    }
}

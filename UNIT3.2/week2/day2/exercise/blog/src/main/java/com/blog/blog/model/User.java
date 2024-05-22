package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private static int count;
    private String name;
    private String surname;
    private String email;
    @JoinColumn(name = "birth_date")
    private LocalDate birthDate;
    private String avatar;
    @OneToMany(mappedBy = "user")
    private List<BlogPost> blogPosts;

    /*public User(String name, String surname, String email, LocalDate birthDate, String avatar) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.avatar = avatar;
        count++;
        this.userId = count;
    }*/
}

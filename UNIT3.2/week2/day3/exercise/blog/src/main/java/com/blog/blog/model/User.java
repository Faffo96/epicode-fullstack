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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

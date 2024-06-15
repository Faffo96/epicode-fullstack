package com.epicode.events_new.Entity;

import com.epicode.events_new.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    private String name;
    private String surname;
    @Id
    private String email;
    private String avatar;
    private String password;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "user_role")
    private UserRole userRole;
    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole == null) {
            throw new IllegalStateException("Role is not set for employee: " + this.email);
        }
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
}

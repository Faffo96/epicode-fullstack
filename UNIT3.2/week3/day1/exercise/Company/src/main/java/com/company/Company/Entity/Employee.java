package com.company.Company.Entity;

import com.company.Company.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue
    @JoinColumn(name = "employee_id")
    private int employeeId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatar;
    private String password;
    @OneToMany(mappedBy = "employee")
    private List<Device> devices;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            throw new IllegalStateException("Role is not set for employee: " + this.email);
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
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

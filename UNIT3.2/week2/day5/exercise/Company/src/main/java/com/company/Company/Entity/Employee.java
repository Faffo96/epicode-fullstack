package com.company.Company.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    @JoinColumn(name = "employee_id")
    private int employeeId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatar;
    @OneToMany(mappedBy = "employee")
    private List<Device> devices;
}

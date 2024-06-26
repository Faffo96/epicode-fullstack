package com.company.Company.Repository;
import com.company.Company.Entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByEmail(String email);
}

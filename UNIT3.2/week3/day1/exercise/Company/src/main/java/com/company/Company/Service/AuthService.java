package com.company.Company.Service;

import com.company.Company.Dto.EmployeeLoginDto;
import com.company.Company.Entity.Employee;
import com.company.Company.Exception.EmailAlreadyInUseException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Exception.UnauthorizedException;
import com.company.Company.Security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateEmployeeAndCreateToken(EmployeeLoginDto employeeLoginDto) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeByEmail(employeeLoginDto.getEmail());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (passwordEncoder.matches(employeeLoginDto.getPassword(), employee.getPassword())) {
                return jwtTool.createToken(employee);
            } else {
                throw new UnauthorizedException("Error in authorization, relogin!");
            }
        } else {
            throw new EmployeeNotFoundException("Employee with email " + employeeLoginDto.getEmail() + " not found.");
        }
    }
}

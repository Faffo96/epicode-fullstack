package com.company.Company.Controller;

import com.company.Company.Dto.EmployeeDto;
import com.company.Company.Dto.EmployeeLoginDto;
import com.company.Company.Exception.BadRequestException;
import com.company.Company.Service.AuthService;
import com.company.Company.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated EmployeeDto employeeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return employeeService.POSTEmployee(employeeDto);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated EmployeeLoginDto employeeLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return authService.authenticateEmployeeAndCreateToken(employeeLoginDto);
    }
}

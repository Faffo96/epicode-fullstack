package com.company.Company.Controller;

import com.company.Company.Dto.EmployeeDto;
import com.company.Company.Exception.BadRequestException;
import com.company.Company.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.company.Company.Entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String benvenuto() {
        return "Welcome!";
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public String POSTEmployee(@RequestBody @Validated EmployeeDto employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return employeeService.POSTEmployee(employee);
    }

    @GetMapping("/employees")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Employee> GETAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "employeeId") String sortBy) {
        return employeeService.GETEmployees(page, size, sortBy);
    }

    @GetMapping("/employees/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Employee GETEmployeeById(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody Employee PUTEmployee(@PathVariable int employeeId, @RequestBody @Validated EmployeeDto employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return employeeService.PUTEmployee(employeeId, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String DELETEEmployee(@PathVariable int employeeId) {
        return employeeService.DELETEEmployee(employeeId);
    }

    @PatchMapping("/employees/{employeeId}")
    public String PATCHEmployeeAvatar(@RequestPart MultipartFile avatar, @PathVariable int employeeId) throws IOException {
        return employeeService.PATCHEmployeeAvatar(employeeId, avatar);
    }
}

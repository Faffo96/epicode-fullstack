package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.EmployeeDto;
import com.company.Company.Enum.Role;
import com.company.Company.Exception.EmailAlreadyInUseException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.company.Company.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");

    public String POSTEmployee(EmployeeDto employeeDto) {
        if (getEmployeeByEmail(employeeDto.getEmail()).isEmpty()) {
            Employee employee = new Employee();
            employee.setUsername(employeeDto.getUsername());
            employee.setName(employeeDto.getName());
            employee.setSurname(employeeDto.getSurname());
            employee.setEmail(employeeDto.getEmail());
            employee.setAvatar(employeeDto.getAvatar());
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            employee.setRole(Role.USER);

            employeeRepository.save(employee);
            loggerTrace.trace("Registration email sent to employee id: " + employee.getEmployeeId());
            sendRegistrationMail(employee);
            loggerTrace.trace("Employee with id " + employee.getEmployeeId() + " saved.");
            return "Employee with id " + employee.getEmployeeId() + " saved.";
        } else {
            throw new EmailAlreadyInUseException("Email " + employeeDto.getEmail() + " already in use.");
        }
    }

    public Page<Employee> GETEmployees(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(int employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);

        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            loggerError.error("Employee id: " + employeeId + " not found.");
            throw new EmployeeNotFoundException("Employee id: " + employeeId + " not found.");
        }
    }

    public Employee PUTEmployee(int employeeId, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(employeeId);

        if (employee != null) {
            employee.setUsername(employeeDto.getUsername());
            employee.setName(employeeDto.getName());
            employee.setSurname(employeeDto.getSurname());
            employee.setEmail(employeeDto.getEmail());
            employee.setAvatar(employeeDto.getAvatar());
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

            employeeRepository.save(employee);
            loggerTrace.trace("Employee with id " + employeeId + " modified.");
            return employee;
        } else {
            loggerError.error("Employee id: " + employeeId + " not found.");
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found.");
        }
    }

    public String DELETEEmployee(int employeeId) {
        Employee employee = getEmployeeById(employeeId);

        if (employee != null) {
            employeeRepository.delete(employee);
            loggerTrace.trace("Employee with id " + employeeId + " deleted successfully.");
            return "Employee with id " + employeeId + " deleted successfully.";
        } else {
            loggerError.error("Employee id: " + employeeId + " not found.");
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found.");
        }
    }

    public String PATCHEmployeeAvatar(int employeeId, MultipartFile photo) throws IOException {
        Employee employee = getEmployeeById(employeeId);

        if (employee != null) {
            String url = (String) cloudinary.uploader().upload(photo.getBytes(), Collections.emptyMap()).get("url");

            employee.setAvatar(url);
            employeeRepository.save(employee);
            loggerTrace.trace("Employee with id=" + employeeId + " updated successfully with the sent photo.");
            return "Employee with id=" + employeeId + " updated successfully with the sent photo.";
        } else {
            loggerError.error("Employee id: " + employeeId + " not found.");
            throw new EmployeeNotFoundException("Employee with id=" + employeeId + " not found.");
        }
    }

    private void sendRegistrationMail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Rest Service Registration");
        message.setText("Congratulations, " + employee.getName() + " " + employee.getSurname() + "! Successful registration to this rest service");

        javaMailSender.send(message);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


}

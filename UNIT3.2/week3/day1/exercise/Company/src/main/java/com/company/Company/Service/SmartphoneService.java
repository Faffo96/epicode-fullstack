package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.SmartphoneDto;
import com.company.Company.Entity.Device;
import com.company.Company.Entity.Smartphone;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.SmartphoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.company.Company.Entity.Employee;

import java.util.Optional;

@Service
public class SmartphoneService {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");
    private static Logger loggerWarn = LoggerFactory.getLogger("loggerWarn");

    public String POSTSmartphone(SmartphoneDto smartphoneDto) {
        Smartphone smartphone = new Smartphone();
        smartphone.setBrand(smartphoneDto.getBrand());
        smartphone.setModel(smartphoneDto.getModel());
        smartphone.setStorageGb(smartphoneDto.getStorageGb());
        smartphone.setDeviceState(smartphoneDto.getDeviceState());
        smartphone.setEmployee(smartphoneDto.getEmployee());
        smartphone.setDualSim(smartphoneDto.isDualSim());

        smartphoneRepository.save(smartphone);
        loggerTrace.trace("Smartphone with id " + smartphone.getDeviceId() + " saved.");
        return "Smartphone with id " + smartphone.getDeviceId() + " saved.";
    }

    public Page<Smartphone> GETSmartphones(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return smartphoneRepository.findAll(pageable);
    }

    public Smartphone GETSmartphoneById(int deviceId) {
        Optional<Smartphone> smartphoneOpt = smartphoneRepository.findById(deviceId);

        if (smartphoneOpt.isPresent()) {
            return smartphoneOpt.get();
        } else {
            loggerError.error("Smartphone id:" + deviceId + " not found.");
            throw new DeviceNotFoundException("Smartphone id: " + deviceId + " not found.");
        }
    }

    public Smartphone PUTSmartphone(int deviceId, SmartphoneDto smartphoneDto) {
        Smartphone smartphone = GETSmartphoneById(deviceId);

        if (smartphone != null) {
            smartphone.setBrand(smartphoneDto.getBrand());
            smartphone.setModel(smartphoneDto.getModel());
            smartphone.setStorageGb(smartphoneDto.getStorageGb());
            smartphone.setDeviceState(smartphoneDto.getDeviceState());
            smartphone.setEmployee(smartphoneDto.getEmployee());
            smartphone.setDualSim(smartphoneDto.isDualSim());

            smartphoneRepository.save(smartphone);
            loggerTrace.trace("Smartphone with id " + smartphone.getDeviceId() + " modified.");
            return smartphone;
        } else {
            loggerError.error("Smartphone id:" + deviceId + " not found.");
            throw new DeviceNotFoundException("Smartphone with id " + deviceId + " not found.");
        }
    }

    public String DELETESmartphone(int deviceId) {
        Smartphone smartphone = GETSmartphoneById(deviceId);

        if (smartphone != null) {
            smartphoneRepository.delete(smartphone);
            loggerTrace.trace("Smartphone with id " + deviceId + " deleted successfully.");
            return "Smartphone with id " + deviceId + " deleted successfully.";
        } else {
            loggerError.error("Smartphone id:" + deviceId + " not found.");
            throw new DeviceNotFoundException("Smartphone with id " + deviceId + " not found.");
        }
    }

    public Smartphone patchSmartphoneEmployee(int deviceId, Employee selectedEmployee) {
        Smartphone smartphone = smartphoneRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Smartphone id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Smartphone id:" + deviceId + " not found.");
                });

        if (smartphone.getDeviceState() != DeviceState.AVAILABLE) {
            String warnMessage = "Cannot assign the smartphone: " + deviceId + ". The state of this " + smartphone.getClass().getSimpleName() + " is: " + smartphone.getDeviceState();
            loggerWarn.warn(warnMessage);
            throw new DeviceNotAvailableException(warnMessage);
        }

        Employee employee = Optional.ofNullable(employeeService.getEmployeeById(selectedEmployee.getEmployeeId()))
                .orElseThrow(() -> {
                    String errorMessage = "Employee with id:" + selectedEmployee.getEmployeeId() + " not found";
                    loggerError.error(errorMessage);
                    return new EmployeeNotFoundException(errorMessage);
                });

        smartphone.setEmployee(employee);
        loggerTrace.trace("Smartphone with id " + deviceId + " assigned to employee id: " + employee.getEmployeeId());
        sendNewDeviceMail(employee, smartphone);
        loggerTrace.trace("Smartphone id: " + deviceId + " assignment email sent to employee id: " + employee.getEmployeeId());
        return smartphoneRepository.save(smartphone);
    }

    public Smartphone removeEmployeeFromSmartphone(int deviceId) {
        Smartphone smartphone = smartphoneRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Smartphone id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Smartphone id:" + deviceId + " not found.");
                });

        Employee employee = Optional.ofNullable(smartphone.getEmployee())
                .orElseThrow(() -> {
                    loggerError.error("Cannot remove the device. No employee associated with Smartphone id:" + deviceId);
                    return new EmployeeNotFoundException("No employee associated with Smartphone id:" + deviceId);
                });

        smartphone.setEmployee(null);

        loggerTrace.trace("Smartphone with id " + deviceId + " removed from employee id: " + employee.getEmployeeId());
        return smartphoneRepository.save(smartphone);
    }

    private void sendNewDeviceMail(Employee employee, Device device) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("You've been assigned a new " + device.getClass().getSimpleName() + "!");
        message.setText("Hi " + employee.getName() + ", " +
                "\n\nYou've been assigned a new " + device.getClass().getSimpleName() + "!" +
                "\n It is an " + device.getBrand() + " " + device.getModel() + " with " + device.getStorageGb() + "GB. Enjoy it!");

        javaMailSender.send(message);
    }
}


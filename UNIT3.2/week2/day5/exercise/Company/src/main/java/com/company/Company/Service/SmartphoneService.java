package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.SmartphoneDto;
import com.company.Company.Entity.Smartphone;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public String POSTSmartphone(SmartphoneDto smartphoneDto) {
        Smartphone smartphone = new Smartphone();
        smartphone.setBrand(smartphoneDto.getBrand());
        smartphone.setModel(smartphoneDto.getModel());
        smartphone.setStorageGb(smartphoneDto.getStorageGb());
        smartphone.setDeviceState(smartphoneDto.getDeviceState());
        smartphone.setEmployee(smartphoneDto.getEmployee());
        smartphone.setDualSim(smartphoneDto.isDualSim());

        smartphoneRepository.save(smartphone);

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

            return smartphone;
        } else {
            throw new DeviceNotFoundException("Smartphone with id " + deviceId + " not found.");
        }
    }

    public String DELETESmartphone(int deviceId) {
        Smartphone smartphone = GETSmartphoneById(deviceId);

        if (smartphone != null) {
            smartphoneRepository.delete(smartphone);
            return "Smartphone with id " + deviceId + " deleted successfully.";
        } else {
            throw new DeviceNotFoundException("Smartphone with id " + deviceId + " not found.");
        }
    }

    public Smartphone patchSmartphoneEmployee(int deviceId, Employee selectedEmployee) {
        Smartphone smartphone = smartphoneRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Smartphone id:" + deviceId + " not found."));

        if (smartphone.getDeviceState() == DeviceState.AVAILABLE) {
            Employee employee = employeeService.getEmployeeById(selectedEmployee.getEmployeeId());

            if (employee != null) {
                smartphone.setEmployee(employee);
                return smartphoneRepository.save(smartphone);
            } else {
                throw new EmployeeNotFoundException("Employee with id:" + selectedEmployee.getEmployeeId() + " not found");
            }
        } else {
            throw new DeviceNotAvailableException("Cannot assign the smartphone: " + deviceId + ". The state of this " + smartphone.getClass().getSimpleName() + " is: " + smartphone.getDeviceState());
        }
    }

}


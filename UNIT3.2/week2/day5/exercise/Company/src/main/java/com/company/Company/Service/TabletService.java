package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.TabletDto;
import com.company.Company.Entity.Tablet;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.EmployeeRepository;
import com.company.Company.Repository.TabletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.company.Company.Entity.Employee;

import java.util.Optional;

@Service
public class TabletService {
    @Autowired
    private TabletRepository tabletRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Cloudinary cloudinary;

    public String POSTTablet(TabletDto tabletDto) {
        Tablet tablet = new Tablet();
        tablet.setBrand(tabletDto.getBrand());
        tablet.setModel(tabletDto.getModel());
        tablet.setStorageGb(tabletDto.getStorageGb());
        tablet.setDeviceState(tabletDto.getDeviceState());
        tablet.setEmployee(tabletDto.getEmployee());
        tablet.setHaveSimSlot(tabletDto.getHaveSimSlot());

        tabletRepository.save(tablet);

        return "Tablet with id " + tablet.getDeviceId() + " saved.";
    }

    public Page<Tablet> GETTablets(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return tabletRepository.findAll(pageable);
    }

    public Tablet GETTabletById(int deviceId) {
        Optional<Tablet> tabletOpt = tabletRepository.findById(deviceId);

        if (tabletOpt.isPresent()) {
            return tabletOpt.get();
        } else {
            throw new DeviceNotFoundException("Tablet id: " + deviceId + " not found.");
        }
    }

    public Tablet PUTTablet(int deviceId, TabletDto tabletDto) {
        Tablet tablet = GETTabletById(deviceId);

        if (tablet != null) {
            tablet.setBrand(tabletDto.getBrand());
            tablet.setModel(tabletDto.getModel());
            tablet.setStorageGb(tabletDto.getStorageGb());
            tablet.setDeviceState(tabletDto.getDeviceState());
            tablet.setEmployee(tabletDto.getEmployee());
            tablet.setHaveSimSlot(tabletDto.getHaveSimSlot());

            tabletRepository.save(tablet);

            return tablet;
        } else {
            throw new DeviceNotFoundException("Tablet with id " + deviceId + " not found.");
        }
    }

    public String DELETETablet(int deviceId) {
        Tablet tablet = GETTabletById(deviceId);

        if (tablet != null) {
            tabletRepository.delete(tablet);
            return "Tablet with id " + deviceId + " deleted successfully.";
        } else {
            throw new DeviceNotFoundException("Tablet with id " + deviceId + " not found.");
        }
    }

    public Tablet patchTabletEmployee(int deviceId, Employee selectedEmployee) {
        Tablet tablet = tabletRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Tablet id:" + deviceId + " not found."));

        if (tablet.getDeviceState() == DeviceState.AVAILABLE) {
            Employee employee = employeeService.getEmployeeById(selectedEmployee.getEmployeeId());

            if (employee != null) {
                tablet.setEmployee(employee);
                return tabletRepository.save(tablet);
            } else {
                throw new EmployeeNotFoundException("Employee with id:" + selectedEmployee.getEmployeeId() + " not found");
            }
        } else {
            throw new DeviceNotAvailableException("Cannot assign the tablet: " + deviceId + ". The state of this " + tablet.getClass().getSimpleName() + " is: " + tablet.getDeviceState());
        }
    }

}
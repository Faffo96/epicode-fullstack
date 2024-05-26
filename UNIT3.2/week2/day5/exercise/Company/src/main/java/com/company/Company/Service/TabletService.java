package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.TabletDto;
import com.company.Company.Entity.Device;
import com.company.Company.Entity.Tablet;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.TabletRepository;
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
public class TabletService {
    @Autowired
    private TabletRepository tabletRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");
    private static Logger loggerWarn = LoggerFactory.getLogger("loggerWarn");

    public String POSTTablet(TabletDto tabletDto) {
        Tablet tablet = new Tablet();
        tablet.setBrand(tabletDto.getBrand());
        tablet.setModel(tabletDto.getModel());
        tablet.setStorageGb(tabletDto.getStorageGb());
        tablet.setDeviceState(tabletDto.getDeviceState());
        tablet.setEmployee(tabletDto.getEmployee());
        tablet.setHaveSimSlot(tabletDto.getHaveSimSlot());

        tabletRepository.save(tablet);
        loggerTrace.trace("Tablet with id " + tablet.getDeviceId() + " saved.");
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
            loggerError.error("Tablet id:" + deviceId + " not found.");
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
            loggerTrace.trace("Tablet with id " + tablet.getDeviceId() + " modified.");
            return tablet;
        } else {
            loggerError.error("Tablet id:" + deviceId + " not found.");
            throw new DeviceNotFoundException("Tablet with id " + deviceId + " not found.");
        }
    }

    public String DELETETablet(int deviceId) {
        Tablet tablet = GETTabletById(deviceId);

        if (tablet != null) {
            tabletRepository.delete(tablet);
            loggerTrace.trace("Tablet with id " + deviceId + " deleted successfully.");
            return "Tablet with id " + deviceId + " deleted successfully.";
        } else {
            loggerError.error("Tablet id:" + deviceId + " not found.");
            throw new DeviceNotFoundException("Tablet with id " + deviceId + " not found.");
        }
    }

    public Tablet patchTabletEmployee(int deviceId, Employee selectedEmployee) {
        Tablet tablet = tabletRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Tablet id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Tablet id:" + deviceId + " not found.");
                });

        if (tablet.getDeviceState() == DeviceState.AVAILABLE) {
            Employee employee = employeeService.getEmployeeById(selectedEmployee.getEmployeeId());

            if (employee != null) {
                tablet.setEmployee(employee);
                loggerTrace.trace("Tablet with id " + deviceId + " assigned to employee id: " + employee.getEmployeeId());
                sendNewDeviceMail(employee, tablet);
                loggerTrace.trace("Device id: " + deviceId + " assignment email sent to employee id: " + employee.getEmployeeId());
                return tabletRepository.save(tablet);
            } else {
                loggerError.error("Tablet id:" + deviceId + " not found.");
                throw new EmployeeNotFoundException("Employee with id:" + selectedEmployee.getEmployeeId() + " not found");
            }
        } else {
            loggerWarn.warn("Cannot assign the Tablet: " + deviceId + ". The state of this " + tablet.getClass().getSimpleName() + " is: " + tablet.getDeviceState());
            throw new DeviceNotAvailableException("Cannot assign the tablet: " + deviceId + ". The state of this " + tablet.getClass().getSimpleName() + " is: " + tablet.getDeviceState());
        }
    }

    public Tablet removeEmployeeFromTablet(int deviceId) {
        Tablet tablet = tabletRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Tablet id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Tablet id:" + deviceId + " not found.");
                });

        tablet.setEmployee(null);
        loggerTrace.trace("Tablet with id " + deviceId + " removed from employee id: " + tablet.getDeviceId());
        return tabletRepository.save(tablet);
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
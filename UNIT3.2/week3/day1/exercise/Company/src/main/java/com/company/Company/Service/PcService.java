package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.PcDto;
import com.company.Company.Entity.Device;
import com.company.Company.Entity.Pc;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.PcRepository;
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

import java.util.Optional;

import com.company.Company.Entity.Employee;

@Service
public class PcService {
    @Autowired
    private PcRepository pcRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");
    private static Logger loggerWarn = LoggerFactory.getLogger("loggerWarn");

    public String POSTPc(PcDto pcDto) {
        Pc pc = new Pc();
        pc.setBrand(pcDto.getBrand());
        pc.setModel(pcDto.getModel());
        pc.setStorageGb(pcDto.getStorageGb());
        pc.setDeviceState(pcDto.getDeviceState());
        pc.setEmployee(pcDto.getEmployee());
        pc.setRam(pcDto.getRam());
        pc.setGpu(pcDto.getGpu());
        pc.setCpu(pcDto.getCpu());
        pc.setLaptop(pcDto.getLaptop());

        pcRepository.save(pc);
        loggerTrace.trace("PC with id " + pc.getDeviceId() + " saved.");
        return "PC with id " + pc.getDeviceId() + " saved.";
    }

    public Page<Pc> GETPcs(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return pcRepository.findAll(pageable);
    }

    public Pc GETPcById(int deviceId) {
        Optional<Pc> pcOpt = pcRepository.findById(deviceId);

        if (pcOpt.isPresent()) {
            return pcOpt.get();
        } else {
            loggerError.error("PC id: " + deviceId + " not found.");
            throw new DeviceNotFoundException("PC id: " + deviceId + " not found.");
        }
    }

    public Pc PUTPc(int deviceId, PcDto pcDto) {
        Pc pc = GETPcById(deviceId);

        if (pc != null) {
            pc.setBrand(pcDto.getBrand());
            pc.setModel(pcDto.getModel());
            pc.setStorageGb(pcDto.getStorageGb());
            pc.setDeviceState(pcDto.getDeviceState());
            pc.setEmployee(pcDto.getEmployee());
            pc.setRam(pcDto.getRam());
            pc.setGpu(pcDto.getGpu());
            pc.setCpu(pcDto.getCpu());
            pc.setLaptop(pcDto.getLaptop());


            pcRepository.save(pc);
            loggerTrace.trace("PC with id " + pc.getDeviceId() + " modified.");
            return pc;
        } else {
            loggerError.error("PC id: " + deviceId + " not found.");
            throw new DeviceNotFoundException("PC with id " + deviceId + " not found.");
        }
    }

    public String DELETEPc(int deviceId) {
        Pc pc = GETPcById(deviceId);

        if (pc != null) {
            pcRepository.delete(pc);
            loggerTrace.trace("PC with id " + deviceId + " deleted successfully.");
            return "PC with id " + deviceId + " deleted successfully.";
        } else {
            loggerError.error("PC id: " + deviceId + " not found.");
            throw new DeviceNotFoundException("PC with id " + deviceId + " not found.");
        }
    }

    public Pc patchPcEmployee(int deviceId, Employee selectedEmployee) {
        Pc pc = pcRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Pc id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Pc id:" + deviceId + " not found.");
                });

        if (pc.getDeviceState() != DeviceState.AVAILABLE) {
            String warnMessage = "Cannot assign the pc: " + deviceId + ". The state of this " + pc.getClass().getSimpleName() + " is: " + pc.getDeviceState();
            loggerWarn.warn(warnMessage);
            throw new DeviceNotAvailableException(warnMessage);
        }

        Employee employee = Optional.ofNullable(employeeService.getEmployeeById(selectedEmployee.getEmployeeId()))
                .orElseThrow(() -> {
                    String errorMessage = "Employee with id:" + selectedEmployee.getEmployeeId() + " not found";
                    loggerError.error(errorMessage);
                    return new EmployeeNotFoundException(errorMessage);
                });

        pc.setEmployee(employee);
        loggerTrace.trace("PC with id " + deviceId + " assigned to employee id: " + employee.getEmployeeId());
        sendNewDeviceMail(employee, pc);
        loggerTrace.trace("PC with id: " + deviceId + " assignment email sent to employee id: " + employee.getEmployeeId());
        return pcRepository.save(pc);
    }


    public Pc removeEmployeeFromPc(int deviceId) {
        Pc pc = pcRepository.findById(deviceId)
                .orElseThrow(() -> {
                    loggerError.error("Pc id:" + deviceId + " not found.");
                    return new DeviceNotFoundException("Pc id:" + deviceId + " not found.");
                });

        Employee employee = Optional.ofNullable(pc.getEmployee())
                .orElseThrow(() -> {
                    loggerError.error("Cannot remove the device. No employee associated with Pc id:" + deviceId);
                    return new EmployeeNotFoundException("No employee associated with Pc id:" + deviceId);
                });

        pc.setEmployee(null);

        loggerTrace.trace("PC with id " + deviceId + " removed from employee id: " + employee.getEmployeeId());
        return pcRepository.save(pc);
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

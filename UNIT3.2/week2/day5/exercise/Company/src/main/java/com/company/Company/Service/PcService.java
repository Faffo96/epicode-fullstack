package com.company.Company.Service;

import com.cloudinary.Cloudinary;
import com.company.Company.Dto.PcDto;
import com.company.Company.Entity.Pc;
import com.company.Company.Enum.DeviceState;
import com.company.Company.Exception.DeviceNotAvailableException;
import com.company.Company.Exception.DeviceNotFoundException;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Repository.PcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

            return pc;
        } else {
            throw new DeviceNotFoundException("PC with id " + deviceId + " not found.");
        }
    }

    public String DELETEPc(int deviceId) {
        Pc pc = GETPcById(deviceId);

        if (pc != null) {
            pcRepository.delete(pc);
            return "PC with id " + deviceId + " deleted successfully.";
        } else {
            throw new DeviceNotFoundException("PC with id " + deviceId + " not found.");
        }
    }

    public Pc patchPcEmployee(int deviceId, Employee selectedEmployee) {
        Pc pc = pcRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Pc id:" + deviceId + " not found."));

        if (pc.getDeviceState() == DeviceState.AVAILABLE) {
            Employee employee = employeeService.getEmployeeById(selectedEmployee.getEmployeeId());

            if (employee != null) {
                pc.setEmployee(employee);
                return pcRepository.save(pc);
            } else {
                throw new EmployeeNotFoundException("Employee with id:" + selectedEmployee.getEmployeeId() + " not found");
            }
        } else {
            throw new DeviceNotAvailableException("Cannot assign the device: " + deviceId + ". The state of this " + pc.getClass().getSimpleName() + " is: " + pc.getDeviceState());
        }
    }
}

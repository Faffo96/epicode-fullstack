package com.company.Company.Controller;

import com.company.Company.Dto.PcDto;
import com.company.Company.Entity.Pc;
import com.company.Company.Exception.BadRequestException;
import com.company.Company.Service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.company.Company.Entity.Employee;

@RestController
@RequestMapping("/api")
public class PcController {
    @Autowired
    private PcService pcService;

    @PostMapping("/pcs")
    @ResponseStatus(HttpStatus.CREATED)
    public String POSTPc(@RequestBody @Validated PcDto pc, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return pcService.POSTPc(pc);
    }

    @GetMapping("/pcs")
    public Page<Pc> GETAllPcs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "deviceId") String sortBy) {
        return pcService.GETPcs(page, size, sortBy);
    }

    @GetMapping("/pcs/{deviceId}")
    public Pc GETPcById(@PathVariable int deviceId) {
        return pcService.GETPcById(deviceId);
    }

    @PutMapping(path = "/pcs/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Pc PUTPc(@PathVariable int deviceId, @RequestBody @Validated PcDto pc, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return pcService.PUTPc(deviceId, pc);
    }

    @DeleteMapping("/pcs/{deviceId}")
    public String DELETEPc(@PathVariable int deviceId) {
        return pcService.DELETEPc(deviceId);
    }

    @PatchMapping(path = "/pcs/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Pc patchPcEmployee(@PathVariable int deviceId, @RequestBody @Validated Employee employee) {
        return pcService.patchPcEmployee(deviceId, employee);
    }

    @PatchMapping("/pcs/{deviceId}/removeEmployee")
    public ResponseEntity<Pc> removeEmployeeFromPc(@PathVariable int deviceId) {
        Pc updatedPc = pcService.removeEmployeeFromPc(deviceId);
        return ResponseEntity.ok(updatedPc);
    }
}

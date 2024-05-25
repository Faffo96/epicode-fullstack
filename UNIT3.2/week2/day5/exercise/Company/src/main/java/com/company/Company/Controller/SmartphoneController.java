package com.company.Company.Controller;

import com.company.Company.Dto.SmartphoneDto;
import com.company.Company.Entity.Employee;
import com.company.Company.Entity.Pc;
import com.company.Company.Exception.BadRequestException;
import com.company.Company.Service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.company.Company.Entity.Smartphone;

@RestController
@RequestMapping("/api")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    @PostMapping("/smartphones")
    @ResponseStatus(HttpStatus.CREATED)
    public String POSTSmartphone(@RequestBody @Validated SmartphoneDto smartphone, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return smartphoneService.POSTSmartphone(smartphone);
    }

    @GetMapping("/smartphones")
    public Page<Smartphone> GETAllSmartphones(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "deviceId") String sortBy) {
        return smartphoneService.GETSmartphones(page, size, sortBy);
    }

    @GetMapping("/smartphones/{deviceId}")
    public Smartphone GETSmartphoneById(@PathVariable int deviceId) {
        return smartphoneService.GETSmartphoneById(deviceId);
    }

    @PutMapping(path = "/smartphones/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Smartphone GETSmartphone(@PathVariable int deviceId, @RequestBody @Validated SmartphoneDto smartphone, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return smartphoneService.PUTSmartphone(deviceId, smartphone);
    }

    @DeleteMapping("/smartphones/{deviceId}")
    public String DELETESmartphone(@PathVariable int deviceId) {
        return smartphoneService.DELETESmartphone(deviceId);
    }

    @PatchMapping(path = "/smartphones/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Smartphone patchSmartphoneEmployee(@PathVariable int deviceId, @RequestBody @Validated Employee employee) {
        return smartphoneService.patchSmartphoneEmployee(deviceId, employee);
    }
}

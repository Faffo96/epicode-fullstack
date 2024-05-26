package com.company.Company.Controller;
import com.company.Company.Dto.TabletDto;
import com.company.Company.Entity.Employee;
import com.company.Company.Exception.BadRequestException;
import com.company.Company.Service.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.company.Company.Entity.Tablet;

@RestController
@RequestMapping("/api")
public class TabletController {
    @Autowired
    private TabletService tabletService;

    @PostMapping("/tablets")
    @ResponseStatus(HttpStatus.CREATED)
    public String POSTTablet(@RequestBody @Validated TabletDto tablet, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return tabletService.POSTTablet(tablet);
    }

    @GetMapping("/tablets")
    public Page<Tablet> GETAllTablets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "deviceId") String sortBy) {
        return tabletService.GETTablets(page, size, sortBy);
    }

    @GetMapping("/tablets/{deviceId}")
    public Tablet GETTabletById(@PathVariable int deviceId) {
        return tabletService.GETTabletById(deviceId);
    }

    @PutMapping(path = "/tablets/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Tablet PUTTablet(@PathVariable int deviceId, @RequestBody @Validated TabletDto tablet, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return tabletService.PUTTablet(deviceId, tablet);
    }

    @DeleteMapping("/tablets/{deviceId}")
    public String DELETETablet(@PathVariable int deviceId) {
        return tabletService.DELETETablet(deviceId);
    }

    @PatchMapping(path = "/tablets/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Tablet patchTabletEmployee(@PathVariable int deviceId, @RequestBody @Validated Employee employee) {
        return tabletService.patchTabletEmployee(deviceId, employee);
    }

    @PatchMapping("/tablets/{deviceId}/removeEmployee")
    public ResponseEntity<Tablet> removeEmployeeFromTablet(@PathVariable int deviceId) {
        Tablet updatedTablet = tabletService.removeEmployeeFromTablet(deviceId);
        return ResponseEntity.ok(updatedTablet);
    }
}

package com.epicode.events_new.Controller;

import com.epicode.events_new.Dto.UserDto;
import com.epicode.events_new.Entity.User;
import com.epicode.events_new.Exception.BadRequestException;
import com.epicode.events_new.Exception.EmailAlreadyInUseException;
import com.epicode.events_new.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {@Autowired
private UserService userService;

    /*@PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public String POSTUser(@RequestBody @Validated UserDto user, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        try {
        return userService.POSTUser(user);
        } catch (EmailAlreadyInUseException e) {
            return "Cannot register the new user. Email " + user.getEmail() + " already in use.";
        }
    }*/

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public Page<User> GETAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "email") String sortBy) {
        return userService.GETUsers(page, size, sortBy);
    }

    @GetMapping("/users/{email}")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public User GETUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PatchMapping("/users/{userEmail}")
    @PreAuthorize("hasAuthority('USER')")
    public String PATCHUserAvatar(@RequestPart MultipartFile avatar, @PathVariable String userEmail) throws IOException {
        return userService.PATCHUserAvatar(userEmail, avatar);
    }
}

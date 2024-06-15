package com.epicode.events_new.Controller;


import com.epicode.events_new.Dto.UserDto;
import com.epicode.events_new.Dto.UserLoginDto;
import com.epicode.events_new.Exception.BadRequestException;
import com.epicode.events_new.Exception.EmailAlreadyInUseException;
import com.epicode.events_new.Service.AuthService;
import com.epicode.events_new.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }
        try {
            userService.POSTUser(userDto);
        } catch (EmailAlreadyInUseException e) {
           System.out.println("Email " + userDto.getEmail() + " is already in use!");
        }
        return "User with email " + userDto.getEmail() + " has been created!";
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UserLoginDto userLoginDto, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return authService.authenticateUserAndCreateToken(userLoginDto);
    }
}
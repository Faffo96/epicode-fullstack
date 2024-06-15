package com.epicode.events_new.Service;

import com.epicode.events_new.Dto.UserLoginDto;
import com.epicode.events_new.Entity.User;
import com.epicode.events_new.Exception.UnauthorizedException;
import com.epicode.events_new.Exception.UserNotFoundException;
import com.epicode.events_new.Security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndCreateToken(UserLoginDto userLoginDto) throws UnauthorizedException, UserNotFoundException {
        User user = userService.getUserByEmail(userLoginDto.getEmail());

        if (user != null) {
            if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                return jwtTool.createToken(user);
            } else {
                throw new UnauthorizedException("Error in authorization, relogin!");
            }
        } else {
            throw new UserNotFoundException("User with email " + userLoginDto.getEmail() + " not found.");
        }
    }
}


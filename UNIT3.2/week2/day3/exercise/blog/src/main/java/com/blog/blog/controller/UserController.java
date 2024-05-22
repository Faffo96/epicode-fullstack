package com.blog.blog.controller;

import com.blog.blog.Dto.UserDto;
import com.blog.blog.Exception.UserNotFoundException;
import com.blog.blog.model.User;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api")
public String benvenuto(){
    return "benvenuto!";
}

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
public String postUser(@RequestBody UserDto user) {
    return userService.postUser(user);
}

@GetMapping("/api/users")
public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "userId") String sortBy) {
        return userService.getUsers(page, size, sortBy);
}

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable int userId) throws UserNotFoundException {
        Optional<User> userOpt = userService.getUserById(userId);

        if(userOpt.isPresent()){
            return userOpt.get();
        }
        else{
            throw new UserNotFoundException("User id: " + userId + " not found.");
        }
    }

    @PutMapping(path = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User putUser(@PathVariable int userId, @RequestBody UserDto user) throws Exception {
        return userService.putUser(userId, user);
    }


    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable int userId) throws Exception{
        return userService.deleteUser(userId);
    }
}

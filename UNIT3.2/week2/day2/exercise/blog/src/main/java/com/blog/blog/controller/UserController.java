package com.blog.blog.controller;

import com.blog.blog.model.User;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
public String postUser(@RequestBody User user) {
    return userService.postUser(user);
}

@GetMapping("/api/users")
public List<User> getAllUsers() {
        return userService.getAllUsers();
}

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable int userId) throws Exception{
        Optional<User> userOpt = userService.getUserById(userId);

        if(userOpt.isPresent()){
            return userOpt.get();
        }
        else{
            throw new Exception("User id: " + userId + " not found.");
        }
    }

    @PutMapping(path = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User putUser(@PathVariable int userId, @RequestBody User user) throws Exception {
        return userService.putUser(userId, user);
    }


    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable int userId) throws Exception{
        return userService.deleteUser(userId);
    }
}

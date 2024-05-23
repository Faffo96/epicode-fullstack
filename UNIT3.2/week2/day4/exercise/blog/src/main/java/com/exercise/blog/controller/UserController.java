package com.exercise.blog.controller;

import com.exercise.blog.Dto.UserDto;
import com.exercise.blog.Exception.BadRequestException;
import com.exercise.blog.model.User;
import com.exercise.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api")
    public String benvenuto() {
        return "benvenuto!";
    }

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String postUser(@RequestBody @Validated UserDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return userService.postUser(user);
    }

    @GetMapping("/api/users")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "userId") String sortBy) {
        return userService.getUsers(page, size, sortBy);
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping(path = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User putUser(@PathVariable int userId, @RequestBody @Validated UserDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return userService.putUser(userId, user);
    }


    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @PatchMapping("/api/users/{userId}")
    public String patchUserPhoto(@RequestPart MultipartFile photo, @PathVariable int userId) throws IOException {
        return userService.patchUserPhoto(userId, photo);
    }
}

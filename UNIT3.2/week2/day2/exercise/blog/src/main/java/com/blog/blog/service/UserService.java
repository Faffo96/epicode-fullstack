package com.blog.blog.service;
import com.blog.blog.Dto.UserDto;
import com.blog.blog.Exception.UserNotFoundException;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String postUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setAvatar(userDto.getAvatar());
        userRepository.save(user);
        return "User with id " + user.getUserId() + " saved.";
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public User putUser(int userId, UserDto userDto) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setBirthDate(userDto.getBirthDate());
            user.setAvatar(userDto.getAvatar());
            userRepository.save(user);
            return user;
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        }
    }

    public String deleteUser(int userId) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
            return "User with id " + userId + " deleted successfully.";
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        }
    }
}

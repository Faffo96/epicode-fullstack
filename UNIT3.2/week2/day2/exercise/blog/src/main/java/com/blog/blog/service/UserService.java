package com.blog.blog.service;

import com.blog.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public Optional<User> getUserById(int userId) {
        return users.stream().filter(user -> user.getUserId()==userId).findFirst();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public String postUser(User user) {
        users.add(user);
        return "Users created with id: " + user.getUserId();
    }

    public User putUser(int userId, User userUpd) throws RuntimeException {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(userUpd.getName());
            user.setSurname(userUpd.getSurname());
            user.setEmail(userUpd.getEmail());
            user.setBirthDate(userUpd.getBirthDate());
            user.setAvatar(userUpd.getAvatar());
            return user;
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    public String deleteUser(int userId) throws RuntimeException {
        Optional<User> userOpt = getUserById(userId);

        if (userOpt.isPresent()) {
            users.remove(userOpt.get());
            return "User " + userId + " removed.";
        } else {
            throw new RuntimeException("User not found.");
        }
    }
}

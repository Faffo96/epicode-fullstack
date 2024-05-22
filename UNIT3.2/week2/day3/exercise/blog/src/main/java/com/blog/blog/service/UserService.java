package com.blog.blog.service;
import com.blog.blog.Dto.UserDto;
import com.blog.blog.Exception.UserNotFoundException;
import com.blog.blog.model.BlogPost;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User getUserById(int userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if(userOpt.isPresent()){
            return userOpt.get();
        }
        else{
            throw new UserNotFoundException("User id: " + userId + " not found.");
        }
    }

    public User putUser(int userId, UserDto userDto) {
        User user = getUserById(userId);
        if (user != null) {
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
        User user = getUserById(userId);
        if (user != null) {
            userRepository.delete(user);
            return "User with id " + userId + " deleted successfully.";
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        }
    }
}

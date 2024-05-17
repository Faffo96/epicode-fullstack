package com.bookingManagement.bookingManagement.Service;

import com.bookingManagement.bookingManagement.Entity.User.User;
import com.bookingManagement.bookingManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void postUser(User user){
        userRepository.save(user);
    }

    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}

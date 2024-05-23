package com.exercise.blog.service;

import com.exercise.blog.Dto.UserDto;
import com.exercise.blog.Exception.UserNotFoundException;
import com.exercise.blog.model.User;
import com.exercise.blog.repository.UserRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public String postUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setAvatar(userDto.getAvatar());
        userRepository.save(user);
        sendMail(user.getEmail());
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

    public String patchUserPhoto(int userId, MultipartFile photo) throws IOException {
        User user = getUserById(userId);

        if(user != null){
            String url = (String) cloudinary.uploader().upload(photo.getBytes(), Collections.emptyMap()).get("url");

            user.setAvatar(url);
            userRepository.save(user);
            return "User con id=" + userId + " aggiornato correttamente con la foto inviata";
        }
        else{
            throw new UserNotFoundException("User con id=" + userId + " non trovato");
        }
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}

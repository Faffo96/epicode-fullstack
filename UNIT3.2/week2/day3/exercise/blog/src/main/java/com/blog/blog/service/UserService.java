package com.blog.blog.service;
import com.blog.blog.Dto.UserDto;
import com.blog.blog.Exception.UserNotFoundException;
import com.blog.blog.model.BlogPost;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import com.cloudinary.Cloudinary;
import lombok.Data;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
        return "User with id " + user.getUserId() + " saved.";
    }

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
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

    public String patchUserAvatar(int userId, MultipartFile avatar) throws IOException {
        Optional<User> userOptional = getUserById(userId);

        if(userOptional.isPresent()){
            String url = (String) cloudinary.uploader().upload(avatar.getBytes(), Collections.emptyMap()).get("url");
            User user = userOptional.get();
            user.setAvatar(url);
            userRepository.save(user);
            return "Studente con matricola=" + userId + " aggiornato correttamente con la foto inviata";
        }
        else{
            throw new UserNotFoundException("Studente con matricola=" + userId + " non trovato");
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

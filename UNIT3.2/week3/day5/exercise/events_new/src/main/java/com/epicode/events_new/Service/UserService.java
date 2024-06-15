package com.epicode.events_new.Service;

import com.cloudinary.Cloudinary;
import com.epicode.events_new.Dto.UserDto;
import com.epicode.events_new.Entity.User;
import com.epicode.events_new.Enum.UserRole;
import com.epicode.events_new.Exception.EmailAlreadyInUseException;
import com.epicode.events_new.Exception.UserNotFoundException;
import com.epicode.events_new.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger loggerError = LoggerFactory.getLogger("loggerError");
    private static Logger loggerTrace = LoggerFactory.getLogger("loggerTrace");

    public String POSTUser(UserDto userDto) throws EmailAlreadyInUseException {
        try {
            getUserByEmail(userDto.getEmail());
            throw new EmailAlreadyInUseException("Email " + userDto.getEmail() + " already in use.");
        } catch (UserNotFoundException e) {
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setAvatar(userDto.getAvatar());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setUserRole(UserRole.USER);

            userRepository.save(user);
            loggerTrace.trace("Registration email sent to user: " + user.getEmail());
            sendRegistrationMail(user);
            loggerTrace.trace("User with email " + user.getEmail() + " saved.");
            return "User with email " + user.getEmail() + " saved.";
        }
    }


    public Page<User> GETUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    private void sendRegistrationMail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Rest Service Registration");
        message.setText("Congratulations, " + user.getName() + " " + user.getSurname() + "! Successful registration to this rest service");

        javaMailSender.send(message);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public String PATCHUserAvatar(String userEmail, MultipartFile photo) throws IOException {
        User user = getUserByEmail(userEmail);

        if (user != null) {
            String url = (String) cloudinary.uploader().upload(photo.getBytes(), Collections.emptyMap()).get("url");

            user.setAvatar(url);
            userRepository.save(user);
            loggerTrace.trace("User with email=" + userEmail + " updated successfully with the sent photo.");
            return "User with email=" + userEmail + " updated successfully with the sent photo.";
        } else {
            loggerError.error("User email: " + userEmail + " not found.");
            return "User with email=" + userEmail + " not found.";
        }
    }
}

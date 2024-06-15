package com.epicode.events_new.Exception;

import com.epicode.events_new.Model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundHandler(UserNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> EventNotFoundHandler(EventNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> UnauthorizedHandler(UnauthorizedException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestHandler(BadRequestException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> EmailAlreadyInUseException(EmailAlreadyInUseException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartecipantsLimitException.class)
    public ResponseEntity<Object> PartecipantsLimitException(PartecipantsLimitException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
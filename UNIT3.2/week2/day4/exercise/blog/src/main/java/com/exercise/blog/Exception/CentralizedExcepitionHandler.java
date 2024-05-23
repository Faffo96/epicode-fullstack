package com.exercise.blog.Exception;

import com.exercise.blog.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedExcepitionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseEntity<Object> BlogPostNotFoundHandler(BlogPostNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundHandler(UserNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestHandler(BadRequestException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

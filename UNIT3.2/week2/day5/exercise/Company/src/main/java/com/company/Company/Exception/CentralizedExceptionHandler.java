package com.company.Company.Exception;

import com.company.Company.Model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> EmployeeNotFoundHandler(EmployeeNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<Object> DeviceNotFoundHandler(DeviceNotFoundException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceNotAvailableException.class)
    public ResponseEntity<Object> DeviceNotAvailableHandler(DeviceNotAvailableException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorDate(LocalDateTime.now());
        error.setErrorState(HttpStatus.CONFLICT);

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
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

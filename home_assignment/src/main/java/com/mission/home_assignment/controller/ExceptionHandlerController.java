package com.mission.home_assignment.controller;

import com.mission.home_assignment.exceptions.PersonAlreadyExistException;
import com.mission.home_assignment.exceptions.PersonNotFoundException;
import com.mission.home_assignment.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @Autowired
    private Response errorResponse;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Invalid input", "please put a valid data");
        errorResponse.setErrors(errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        System.out.println("heree");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            String fieldNameSub = fieldName.substring(fieldName.indexOf(".") + 1, fieldName.length());
            errors.put(fieldNameSub, errorMessage);

        });
        errorResponse.setErrors(errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PersonAlreadyExistException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(PersonAlreadyExistException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Conflict", e.getMessage());
        errorResponse.setErrors(errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Not Found", e.getMessage());
        errorResponse.setErrors(errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}


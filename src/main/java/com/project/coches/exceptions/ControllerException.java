package com.project.coches.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity<Map<String, String>> emaiValidationException(EmailValidationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("Error", e.getMessage()));
    }
}

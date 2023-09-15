package com.project.coches.exceptions;

public class EmailValidationException extends RuntimeException {

    public EmailValidationException() {
        super("Email is not valid");
    }
}

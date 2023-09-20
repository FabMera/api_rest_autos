package com.project.coches.exceptions;

public class EmailValidationException extends RuntimeException {

    public EmailValidationException() {
        super("Email no cumple con el formato requerido,debe incluir @ y .");
    }
}

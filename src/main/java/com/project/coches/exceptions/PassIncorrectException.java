package com.project.coches.exceptions;

public class PassIncorrectException extends RuntimeException{
    public PassIncorrectException() {
        super("La contraseña es incorrecta");
    }
}

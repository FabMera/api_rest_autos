package com.project.coches.exceptions;

public class CustomerExistsException extends RuntimeException {
    public CustomerExistsException() {
        super("El usuario ya se encuentra registrado");
    }
}

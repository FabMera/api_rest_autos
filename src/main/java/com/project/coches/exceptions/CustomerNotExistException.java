package com.project.coches.exceptions;

public class CustomerNotExistException extends RuntimeException{
    public CustomerNotExistException() {
        super("El usuario no existe");
    }
}

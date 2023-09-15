package com.project.coches.exceptions;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException() {
        super("No autorizado,no tiene los permisos para realizar esta accion");
    }
}

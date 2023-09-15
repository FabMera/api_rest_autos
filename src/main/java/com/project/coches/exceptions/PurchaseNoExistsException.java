package com.project.coches.exceptions;

public class PurchaseNoExistsException extends RuntimeException{
    public PurchaseNoExistsException() {
        super("La compra no existe");
    }
}

package com.example.quickmart.exceptions.client;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException() {
        super("Client already exists: ");
    }
}

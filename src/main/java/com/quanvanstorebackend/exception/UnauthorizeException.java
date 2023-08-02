package com.quanvanstorebackend.exception;

public class UnauthorizeException extends RuntimeException {
    public UnauthorizeException(String message) {
        super(message);
    }
}

package com.quanvanstorebackend.exception;

public class WrongUsernamPasswordException extends RuntimeException {
    public WrongUsernamPasswordException(String message) {
        super(message);
    }
}

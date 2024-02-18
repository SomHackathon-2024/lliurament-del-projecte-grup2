package com.tecnocampus.hackathon.exception;

public class UserCantDeleteSelfException extends RuntimeException {
    public UserCantDeleteSelfException(String message) {
        super(message);
    }
}

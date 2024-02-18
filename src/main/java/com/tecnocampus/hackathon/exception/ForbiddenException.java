package com.tecnocampus.hackathon.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super("Forbidden: "+message);
    }

    public ForbiddenException() {
        super("Access Denied");
    }
}

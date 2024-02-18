package com.tecnocampus.hackathon.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("Bad Request: "+message);
    }

    public BadRequestException() {
        super("Bad Request");
    }
}

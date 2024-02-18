package com.tecnocampus.hackathon.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Object error;
    
    public ErrorResponse(Object error) {
        this.error = error;
    }
}

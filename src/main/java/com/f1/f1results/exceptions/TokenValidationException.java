package com.f1.f1results.exceptions;

public class TokenValidationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Invalid token";
    }
}

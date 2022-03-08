package com.f1.f1results.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
public class ApiError {

    private final HttpStatus status;
    private final Date timestamp;
    private final List<String> errors;

    public ApiError(HttpStatus status, Date timestamp, List<String> errors) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, Date timestamp, String error) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        errors = List.of(error);
    }
}

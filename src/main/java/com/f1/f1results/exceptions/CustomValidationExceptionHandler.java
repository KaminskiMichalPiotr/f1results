package com.f1.f1results.exceptions;

import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomValidationExceptionHandler extends ResponseEntityExceptionHandler {


    // error handle for @Valid
    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid
    (MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, HttpStatus status, @NonNull WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = {SQLException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = new ArrayList<>();
        if (ex.getCause().getCause().getMessage() != null)
            errors.add(ex.getCause().getCause().getMessage());
        body.put("errors", errors);
        return handleExceptionInternal(ex, body,
                new HttpHeaders(), status, request);
    }


}

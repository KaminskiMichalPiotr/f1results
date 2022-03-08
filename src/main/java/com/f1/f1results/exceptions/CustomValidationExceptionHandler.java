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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomValidationExceptionHandler extends ResponseEntityExceptionHandler {


    // error handle for @Valid
    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid
    (MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {

//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
//        body.put("errors", errors);
        ApiError apiError = new ApiError(status, new Date(), errors);
        return new ResponseEntity<>(apiError, headers, status);
    }

    @ExceptionHandler(value = {SQLException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        List<String> errors = new ArrayList<>();
        if (ex.getCause().getCause().getMessage() != null)
            errors.add(ex.getCause().getCause().getMessage());
        ApiError apiError = new ApiError(status, new Date(), errors);
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = {IncorrectParamException.class})
    protected ResponseEntity<Object> handleValidationError(Exception exception) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiError apiError = new ApiError(status, new Date(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}

package com.f1.f1results.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Id not found")
public class IdNotFoundException extends RuntimeException {
}

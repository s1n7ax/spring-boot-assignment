package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Record already exists")
public class ConflictException extends RuntimeException {
  public ConflictException(String message) {
    super(message);
  }
}
